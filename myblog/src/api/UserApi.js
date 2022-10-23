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
            method:'POST',
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
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 获取所有用户信息
     * @returns 
     */
    getUserInfoList(){
        return axios({
            url:'/user/list/info',
            method:'GET',
        })
    }
}