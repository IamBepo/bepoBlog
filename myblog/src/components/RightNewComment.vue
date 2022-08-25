<template>
    <div class="NewContainer">
        <div style="font-size: 20px;color: #4d4f50;border-bottom: solid 1px #efd4d4;">
            <div style="border-bottom: solid 2px red;width:fit-content;">
                最新评论
            </div>
        </div>

        <div style="margin-top: 20px;">
            <el-timeline style="padding: 0;">
                <el-timeline-item :timestamp="item.updateTime" placement="top" v-for="item in list" :key="item.id">
                    <el-card>
                        <h4 class="testS" @click="toRead(item.articleId)">{{item.title}}</h4>
                        <div class="testS2">
                            @{{item.userInfo.name}} - {{item.content}}
                        </div>
                    </el-card>
                </el-timeline-item>
            </el-timeline>
        </div>

    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {useRouter} from 'vue-router'

    defineProps({
        list:Object
    })

    let router = useRouter()
    function toRead(id){
        let routeUrl = router.resolve({
            path:`/read/${id}`
        })
        window.open(routeUrl.href, '_blank');
    }

</script>

<style scoped>
    .NewContainer {
        box-shadow: 0 0 4px 0 rgb(0 0 0 / 20%);
        border-radius: 5px;
        padding: 20px;
        margin-top: 10px;
        transition: all .3s linear;
        background-color: white;
    }
    .NewContainer:hover {
        box-shadow: 0 0 8px 0 rgb(0 0 0 / 20%);
        -webkit-box-shadow:  0 0 15px 0 rgb(0 0 0 / 20%);
        -webkit-transition: all .5s ease-out;
        transition: all .5s ease-out;
    }
    h4 {
        margin: 0 0 10px 0;
    }
    .testS {

        overflow: hidden;
        text-overflow:ellipsis;
        white-space: nowrap;
    }
    .testS:hover {
        cursor: pointer;
        color: #778be6;
    }
    .testS2 {
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 3;
        overflow: hidden;
    }
</style>