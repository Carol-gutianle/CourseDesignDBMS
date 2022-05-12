import Axios from 'axios';
var root = process.env.API_ROOT;

axios.interceptors.request.use((config)=>{
  config.url = root + config.url;
  return config;
});
