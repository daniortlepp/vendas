import axios from "axios";

export default axios.create({
  baseURL: "http://localhost:8080/apivendas",
  headers: {
    "Content-type": "application/json"
  }
});