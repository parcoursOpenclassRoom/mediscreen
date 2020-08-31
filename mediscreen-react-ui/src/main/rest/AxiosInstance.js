import axios from "axios";
import promise from "promise";

let AxiosInstance = axios.create();

AxiosInstance.interceptors.request.use(
    function (config) {
        return config;
    },
    function (error) {
        // Do something with request error
        return promise.reject(error);
    }
);

export default AxiosInstance;
