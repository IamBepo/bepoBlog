import axios from '../plugins/axios'
import qs from 'qs'

export default {
    /**
     * 获取所有未被推荐
     * @returns 
     */
    getNoRecommendList(){
        return axios({
            url:'/recommend/list/no/list',
            method:'GET'
        })
    },

    /**
     * 获取所有未被推荐走马灯
     * @returns 
     */
    getNoWalkRecommendList(){
        return axios({
            url:'/recommend/list/no/walk',
            method:'GET'
        })
    },

    /**
     * 获取所有推荐
     * @returns 
     */
    getRecommendList(){
        return axios({
            url:'/recommend/list/all/list',
            method:'GET'
        })
    },

    /**
     * 获取走马灯推荐
     * @returns 
     */
    getWalkRecommend(){
        return axios({
            url:'/recommend/list/all/walk',
            method:'GET'
        })
    },


    /**
     * 更新文章推荐权重
     * @param {*} data 
     * @returns 
     */
    updateArticlePower(data){
        return axios({
            url:'/recommend/update/power',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 更新文章推荐状态
     * @param {*} data 
     * @returns 
     */
    updateArticleRecommendStatus(data) {
        return axios({
            url:'blog/update/status/recommend',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 更新文章置顶开关状态
     * @param {*} data 
     * @returns 
     */
    updateArticleTopStatus(data) {
        return axios({
            url:'blog/update/status/top',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 新增推荐
     * @param {*} type 
     * @param {*} data 
     * @returns 
     */
    addArticleRecommend(type,data) {
        return axios({
            url:type==='LIST'?'/recommend/save/list/' + data:'/recommend/save/walk/'+ data,
            method:'GET'
        })
    },

    /**
     * 删除文章推荐
     * @param {*} data 
     * @returns 
     */
    removeArticleRecommend(data) {
        return axios({
            url:'/recommend/remove',
            method:'POST',
            data:qs.stringify(data)
        })
    }
}