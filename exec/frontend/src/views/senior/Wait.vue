<template>
    <div class="black">

    </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import Sha256 from "@/lib/sha256.js"

export default {
    name:"Wait",
    created(){
        this.connect();
    },
    methods:{
        getIpAddress(){
            return fetch('https://api.ipify.org?format=json')
            .then(x => x.json())
            .then(({ ip }) => {
                console.log(Sha256(ip));
                this.$store.commit("setIpHash",Sha256(ip));
            });
        },
        connect() {
            const serverURL = "http://localhost:8000/itda/vuejs";
            
            let Socket = new SockJS(serverURL);
            this.StompClient = Stomp.over(Socket);
            this.StompClient.debug = () => {};
            this.StompClient.connect(
                {},
                (frame) => {
                    // 소켓 연결 성공
                    this.connected = true;
                    frame;
              
                    this.getIpAddress();
                    
                    this.StompClient.subscribe(
                        "/socket/" + this.$store.state.ipHash + "/send",
                        (res) => {
                            console.log(res.body);
                            
                            if(res.body == "ready")
                                this.$router.push({name: 'Splash'});
                        }
                    );
                },
                (error) => {
                    // 소켓 연결 실패
                    console.log("소켓 연결 실패", error);
              this.connected = false;
                }
            );
        }
    }
}
</script>

<style>
.black{
    width:100%;
    height: 100vh;
    background-color:#000000;
}
</style>