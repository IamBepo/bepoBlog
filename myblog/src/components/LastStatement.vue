<template>
    <div class="friendBox">
        <div style="width: 800px;">
            <div style="color: aliceblue;padding: 0 0 10px 0;">友情链接：</div>
            <div style="background-color: #424242;padding: 10px 20px;display: flex;flex-wrap: wrap;width: 100%;">
                <div style="padding: 2px 5px 5px 2px;" v-for="item in friendData" :key="item.name">
                    <a :href="item.url" style="color: aliceblue;" target="_blank">{{item.name}}</a>
                </div>
            </div>
        </div>
    </div>
    <div class="statementBox">
        {{$store.state.systemInfo.underIntroduction}}
    </div>
    <div class="powerBox">
        <div>
            Powered by <a href="https://github.com/IamBepo" style="color: aliceblue;" target="_blank">{{$store.state.systemInfo.underPoweredBy}}</a>
        </div>
        <el-divider direction="vertical" />
        <div>
            Copyright © {{$store.state.systemInfo.underCopyright}}
        </div>
        <el-divider direction="vertical" />
        <div>
            <a href="https://beian.miit.gov.cn/#/Integrated/index" style="color: aliceblue;"
                target="_blank">互联网ICP备案：{{$store.state.systemInfo.underIco}}</a>
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";

let friendData = [
    {
        name:'Bepo博客',
        url:'http://www.llxuo.top'
    },
    {
        name:'百度',
        url:'https://www.baidu.com'
    },
]
</script>

<style scoped>
    a {
        text-decoration: none;
        color: #181717;
    }
    a:visited {
        color: #181717;
    }
    .powerBox {
        height: 30px;
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #2D2D2D;
        color: aliceblue;
    }
    .friendBox {
        width: 100%;
        background-color: #313131;
        display: flex;
        justify-content: center;
        padding: 20px 0;
    }
    .statementBox {
        background-color: #2D2D2D;
        width: 100%;
        display: flex;
        justify-content: center;
        padding: 10px 0;
        color: rgb(173, 197, 197);
    }
</style>