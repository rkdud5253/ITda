<template>
  <v-container class="mb-10">
    <h2>TEAM <span style="color: #FEA601">404 All Found</span></h2>

    <!-- 팀원소개 컴포넌트-->
    <div class="my-2">
      <v-row>
        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <v-img
            class="img-function"
            :src="require('@/assets/family/member1.jpg')"
            gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
          >
            <v-row
              class="fill-height text-right ma-0"
            >
              <v-col cols="12">
                <v-chip
                  label
                  class="text-uppercase my-1"
                  color="#FFFFFF"
                >
                  <h4>김현지</h4>
                </v-chip>
              </v-col>
            </v-row>
          </v-img>
        </v-col>

        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/member2.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                  >
                    <h4>신가영</h4>
                  </v-chip>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>

        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/member3.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                  >
                    <h4>이경섭</h4>
                  </v-chip>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>

        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/member4.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                  >
                    <h4>이병희</h4>
                  </v-chip>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>

        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/member5.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                  >
                    <h4>최다애</h4>
                  </v-chip>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>

        <v-col 
          cols="6"
          sm="4"
          lg="2"
        >
          <div>
            <v-img
              class="img-function"
              :src="require('@/assets/family/member6.jpg')"
              gradient="rgba(0, 0, 0, 0.30), rgba(0, 0, 0, 0.30)"
            >
              <v-row
                class="fill-height text-right ma-0"
              >
                <v-col cols="12">
                  <v-chip
                    label
                    class="text-uppercase my-1"
                    color="#FFFFFF"
                  >
                    <h4>홍진표</h4>
                  </v-chip>
                </v-col>
              </v-row>
            </v-img>
          </div>
        </v-col>

      </v-row>
    </div>

  </v-container>
</template>

<script>
export default {
  name: 'AboutTeam',
}
</script>

<style>

</style>