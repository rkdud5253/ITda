import axios from 'axios'

export default axios.create({
  baseURL: "http://j4a404.p.ssafy.io:8000/itda/",
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-type': 'application/json'
  }
})