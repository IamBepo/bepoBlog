<template>
    <div class="bloggerContainer">
        <div style="color: #fcffeb;font-size: 23px;padding-bottom: 15px;">
            博客概况
        </div>
        <div style="display: flex;justify-content: center;margin:0 0 10px 0;">
            <div style="border-right: solid 1px #ffdcdc;padding-right: 10px;font-size: 25px;">
                <div style="color: #fcffeb;"><em>5</em></div>
                <div style="font-size: 16px;color: #d5ffc7;">文章</div>
            </div>
            <div style="padding-left: 10px;font-size: 25px;border-right: solid 1px #ffdcdc;padding-right: 10px;">
                <div style="color: #fcffeb;"><em>5</em></div>
                <div style="font-size: 16px;color: #d5ffc7;">分类</div>
            </div>
            <div style="padding-left: 10px;font-size: 25px;">
                <div style="color: #fcffeb;"><em>5</em></div>
                <div style="font-size: 16px;color: #d5ffc7;">标签</div>
            </div>
        </div>
        <el-divider />
        <div class="refBox">
            <a :href="item.aU" v-for="(item,index) in imgurl" :key="index" style="margin-bottom: 5px;" target="_blank">
                <img :src="item.imgU" />
                <br /></a>
        </div>
    </div>
</template>

<script>
export default {
    name:'RightBloggerInfo',
    data () {
        return {
            imgurl:[
                {
                    imgU:'https://img.shields.io/badge/github-IBepo-bepo?logo=github',
                    aU:'https://github.com/IamBepo'
                },
                {
                    imgU:'https://img.shields.io/badge/bilibili-IamBepo-bepo?logo=bilibili',
                    aU:'https://space.bilibili.com/130756553?spm_id_from=333.1007.0.0'
                },
                {
                    imgU:'https://img.shields.io/badge/CSDN-%E5%96%94%E5%9A%AF%E5%9A%AF%E1%BB%9B%20%E2%82%83%E1%BB%9DBepo-bepo?logo=Bitrise',
                    aU:'https://blog.csdn.net/qq_15029233?type=blog'
                }
            ]
        }
    }
}
</script>

<style scoped>
    .bloggerContainer {
        box-shadow: 0 0 4px 0 rgb(0 0 0 / 20%);
        border-radius: 5px;
        text-align: center;
        padding: 20px 0 10px 0;
        transition: all .3s linear;
        background-color:#a79595;
        height: 270px;
    }
    .bloggerContainer:hover {
        box-shadow: 0 0 8px 0 rgb(0 0 0 / 20%);
        -webkit-box-shadow:  0 0 15px 0 rgb(0 0 0 / 20%);
        -webkit-transition: all .5s ease-out;
        transition: all .5s ease-out;
    }
    .refBox a img{
        width: 150px;
    }
    /* .imgBox img {
        width: 80px;
        height: 80px;
        border: 3px solid #1aa4e9;
        border-radius: 50%;
        transition: all 0.6s;
    }
    .imgBox img:hover {
        transform: rotate(360deg);
        border-color: rgb(150, 198, 240);
    } */
</style>