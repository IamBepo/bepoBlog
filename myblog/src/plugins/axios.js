import Axios from 'axios'
import { ElMessage } from 'element-plus'

const axois = Axios.create({
	baseURL: 'http://localhost:7777', //请求后端数据的基本地址，自定义
	timeout: 10000,                   //请求超时设置，单位ms
	header: 'Content-Type:application/x-www-form-urlencoded'
})

// request拦截器
axois.interceptors.request.use(config => {
	let token = localStorage.getItem('token')
	if (token) {
		config.headers['Authorization'] = token // 让每个请求携带自定义token
	}
	return config
}, error => {
	console.log(error) // for debug
})

// respone拦截器
axois.interceptors.response.use(
	response => {
		/**
		 * code为非200是抛错 可结合自己业务进行修改
		 * 如果非200，响应在此被拦截，具体调用代码中无需再判断是否为200
		 */
		const res = response.data

		if (res.code != '200') {
			ElMessage({
                message: '网络似乎出现了问题.',
                type: 'error',
            })
			return Promise.reject('error')
		} else {
			return response
		}
	},
	error => {
		ElMessage({
			message: error.message,
			type: 'error',
		})
		console.log('错误:' + error.message)// for debug
		var message;
		if (error.response.status == 504) {
			console.log('连接超时')
		} else {
			console.log(error)
		}
		return Promise.reject(error)
	}
)

export default axois