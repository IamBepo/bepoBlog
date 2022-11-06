import axios from '../plugins/axios'
import qs from 'qs'

export default {
    /**
     * 聚合获取首页展示
     * @param {*} data 
     * @returns 
     */
    getHomeShow() {
        return axios({
            url:'/blog/home',
            method:'GET',
        })
    },
}