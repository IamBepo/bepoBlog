import axios from '../plugins/axios'
import qs from 'qs'

export default {
    /**
     * 登录功能
     * @param {*} data 
     * @returns 
     */
    signIn(data){
        return axios({
            url:'/user/signIn',
            method:'post',
            data:qs.stringify(data)
        })
    },

    /**
     * 注册
     * @param {*} data 
     * @returns 
     */
    signUp(data) {
        return axios({
            url:'/user/signUp',
            method:'post',
            data:qs.stringify(data)
        })
    }
}