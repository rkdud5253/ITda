import axios from 'axios'

export default axios.create({
  baseURL: "http://localhost:8000/itda/",
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-type': 'application/json; charset=utf-8'
  }
})