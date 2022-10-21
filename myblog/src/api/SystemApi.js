import axios from '../plugins/axios'
import qs from 'qs'

export default {
    /**
     * 获取权限路由
     * @param {*} data 
     * @returns 
     */
    getAuthorizationRouter(data) {
        return axios({
            url:'/user/authorization',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 获取博客基本信息
     * @returns 
     */
    getBlogInfo(){
        return axios({
            url: '/system/get/info',
            method: 'GET'
        })
    },

    /**
     * 获取所有友情链接
     * @returns 
     */
    getFriendKLinkList(){
        return axios({
            url:'/system/list/friend',
            method:'GET',
        })
    },

    
    /**
     * 更新博客基础信息
     * @param {*} data 
     * @returns 
     */
    updateBasicInfo(data) {
        return axios({
            url:'system/update/info',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 更新盾牌
     * @param {*} data 
     * @returns 
     */
    updateShields(data) {
        return axios({
            url: '/system/update/shields',
            method: 'POST',
            data: data
        })
    },

    /**
     * 新增友情链接
     * @param {*} data 
     * @returns 
     */
    addFriendLink(data) {
        return axios({
            url:'/system/save/friend',
            method:'POST',
            data: data
        })
    },

    /**
     * 删除友情链接
     * @param {*} id 
     * @returns 
     */
    removeFriendLink(id) {
        return axios({
            url:'/system/remove/friend/' + id,
            method:'GET',
        })
    }
}