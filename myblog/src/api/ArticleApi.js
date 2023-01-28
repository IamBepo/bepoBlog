import axios from '../plugins/axios'
import qs from 'qs'

export default {
    /**
     * 获取标签
     * @returns 
     */
    getTabList() {
        return axios({
          url: '/blog/tab/list',
          method: 'GET'
        })
    },

    /**
     * 获取后台文章
     * @returns 
     */
    getAdminArticleList(){
        return axios({
            url:'/blog/list',
            method:'GET'
        })
    },

    /**
     * 获取分页文章
     * @returns 
     */
    getArticleList() {
        return axios({
            url: '/blog/list/home',
            method: 'GET'
        })
    },

    /**
     * 获取最新评论
     * @returns 
     */
    getNewCommentList(){
        return axios({
            url:'/comment/list/new',
            method:'GET'
        })
    },

    /**
     * 获取推荐
     * @returns 
     */
    getRecommendList(){
        return axios({
            url:'/recommend/list/all/list',
            method:'GET'
        })
    },
    
    /**
     * 获取走马灯
     * @returns 
     */
    getWalkRecommendList(){
        return axios({
            url:'/recommend/list/all/walk',
            method:'GET'
        })
    },

    /**
     * 获取盾牌
     * @returns 
     */
    getShieldsList(){
        return axios({
            url: '/system/list/shields',
            method: 'GET'
        })
    },

    /**
     * 获取文章内容
     * @param {*} id 
     * @returns 
     */
    getArticleContent(id){
        return axios({
            url: '/blog/read/' + id,
            method: 'GET'
        })
    },

    /**
     * 获取文章评论
     * @param {*} id 
     * @returns 
     */
    getArticleComment(id){
        return axios({
            url: '/comment/list/' + id,
            method: 'GET',
        })
    },

    /**
     * 获取分类
     * @returns 
     */
    getSortList(){
        return axios({
            url:'/blog/sort/list',
            method:'GET'
        })
    },

    /**
     * 获取封面
     * @returns 
     */
    getCoverList(){
        return axios({
            url:'blog/cover/list',
            method:'GET'
        })
    },

    /**
     * 获取搜索文章
     * @param {*} data 
     * @returns 
     */
    getSearchArticleList(data){
        return axios({
            url:'/blog/search',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 获取搜索分类
     * @param {*} data 
     * @returns 
     */
    getSearchSort(data){
        return axios({
            url:'/blog/sort/search',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 获取搜索标签
     * @param {*} data 
     * @returns 
     */
    getSearchTab(data){
        return axios({
            url:'/blog/tab/search',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 获取文章所有评论
     * @returns 
     */
    getAllArticleCommentList(){
        return axios({
            url:'comment/list/all',
            method:'GET'
        })
    },

    /**
     * 获取搜索文章
     * @param {*} data 
     * @returns 
     */
    getSearchArticle(data) {
        return axios({
            url:'/blog/list/search',
            method:'POST',
            data:qs.stringify(data)
        })
    },


    /**
     * 更新文章可视状态
     * @param {*} data 
     * @returns 
     */
    updateArticleStatus(data){
        return axios({
            url:'blog/update/status/vs',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 更新分类开关状态
     * @param {*} data 
     * @returns 
     */
    updateSortStatus(data){
        return axios({
            url:'/blog/sort/update/status',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 更新文章评论开关状态
     * @param {*} data 
     * @returns 
     */
    updateArticleCommentStatus(data){
        return axios({
            url:'blog/update/status/comment',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 更新标签开关状态
     * @param {*} data 
     * @returns 
     */
    updateTabStatus(data){
        return axios({
            url:'/blog/tab/update/status',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 批量删除文章
     * @param {*} data 
     * @returns 
     */
    removeMulArticle(data) {
        return axios({
            url:'/blog/remove/arr',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 单个删除文章
     * @param {*} id 
     * @returns 
     */
    removeOnceArticle(id){
        return axios({
            url:'/blog/remove/' + id,
            method:'GET'
        })
    },

    /**
     * 删除单个分类
     * @param {*} id 
     * @returns 
     */
    removeOnceSort(id){
        return axios({
            url:'blog/sort/remove/' + id,
            method:'POST',
        })
    },

    /**
     * 批量删除评论
     * @param {*} data 
     * @returns 
     */
    removeMulComment(data) {
        return axios({
            url:'comment/remove/arr',
            method:'POST',
            data:data
        })
    },

    /**
     * 删除单个评论
     * @param {*} data 
     * @returns 
     */
    removeOnceComment(data) {
        return axios({
            url:'comment/remove/once',
            method:'POST',
            data:data
        })
    },

    /**
     * 删除单个标签
     * @param {*} id 
     * @returns 
     */
    removeOnceTab(id){
        return axios({
            url:'/blog/tab/delete/' + id,
            method:'POST',
        })
    },

    /**
     * 删除单个封面
     * @param {*} id 
     * @returns 
     */
    removeOnceCover(id) {
        return axios({
            url:'blog/cover/remove/' + id,
            method:'GET'
        })
    },

    /**
     * 更新分类内容
     * @param {*} data 
     * @returns 
     */
    updateSortContent(data){
        return axios({
            url:'/blog/sort/update/name',
            method:'POST',
            data:qs.stringify(data)
        })
    },

    /**
     * 更新标签内容
     * @param {*} data 
     * @returns 
     */
    updateTabContent(data){
        return axios({
            url:'/blog/tab/update/exit',
            method:'post',
            data:qs.stringify(data)
        })
    },

    /**
     * 新增文章
     * @param {*} data 
     * @returns 
     */
    addArticle(data){
        return axios({
            url:'/blog/save',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 新增分类
     * @param {*} data 
     * @returns 
     */
    addSort(data){
        return axios({
            url:'/blog/sort/save',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 新增标签
     * @param {*} data 
     * @returns 
     */
    addLabel(data){
        return axios({
            url:'/blog/tab/save',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 新增父评论
     * @param {*} data 
     * @returns 
     */
    addFatherComment(data) {
        return axios({
            url:'/comment/save/first',
            method:'POST',
            data: qs.stringify(data)
        })
    },

    /**
     * 新增子评论
     * @param {*} data 
     * @returns 
     */
    addChildrenComment(data) {
        return axios({
            url: '/comment/save/second',
            method: 'POST',
            data: qs.stringify(data)
        })
    }

}