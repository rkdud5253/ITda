import axios from 'axios'

export default axios.create({
  baseURL: "http://j4a404.p.ssafy.io/itda/",
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-type': 'application/json'
  }
})