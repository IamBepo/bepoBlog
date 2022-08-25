import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import store from './store'
import router from './router'
import './assets/confont/iconfont'  //图标库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import axiosJs from './plugins/axios.js'
import qs from 'qs'
// markdown
import VueMarkdownEditor from '@kangc/v-md-editor';
import '@kangc/v-md-editor/lib/style/base-editor.css';
import githubTheme from '@kangc/v-md-editor/lib/theme/github.js';
import '@kangc/v-md-editor/lib/theme/style/github.css';
import hljs from 'highlight.js';
import createTipPlugin from '@kangc/v-md-editor/lib/plugins/tip/index';
import '@kangc/v-md-editor/lib/plugins/tip/tip.css';
import createEmojiPlugin from '@kangc/v-md-editor/lib/plugins/emoji/index';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
import createLineNumbertPlugin from '@kangc/v-md-editor/lib/plugins/line-number/index';
import createCopyCodePlugin from '@kangc/v-md-editor/lib/plugins/copy-code/index';
import '@kangc/v-md-editor/lib/plugins/copy-code/copy-code.css';
import createAlignPlugin from '@kangc/v-md-editor/lib/plugins/align';
import VMdPreview from '@kangc/v-md-editor/lib/preview';
import '@kangc/v-md-editor/lib/style/preview.css';

VueMarkdownEditor.use(githubTheme, {
    Hljs: hljs,
}).use(createTipPlugin()).use(createEmojiPlugin()).use(createLineNumbertPlugin()).use(createCopyCodePlugin()).use(createAlignPlugin());
VMdPreview.use(githubTheme, {
    Hljs: hljs,
});

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.provide('$axios', axiosJs).provide('$qs',qs).provide('$store',store)
app.use(store)
    .use(ElementPlus)
    .use(VueMarkdownEditor)
    .use(VMdPreview)
    .use(router)
    .mount('#app')