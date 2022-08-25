import Axios from 'axios'

const axois = Axios.create({
	baseURL:'http://localhost:7777', //请求后端数据的基本地址，自定义
	// timeout: 2000,                   //请求超时设置，单位ms
})

export default axois