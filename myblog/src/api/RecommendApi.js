import axios from '../plugins/axios'
import qs from 'qs'

export default {
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
    }
}