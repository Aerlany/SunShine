<template>
    <div class="swagger-container">
        <div class="header">
            <div class="title-section">
                <h1>API 文档中心</h1>
                <p>基于 Swagger UI 的交互式 API 文档</p>
            </div>
            <div class="controls">
                <el-button type="primary" icon="el-icon-refresh" @click="refreshSwagger" :loading="refreshing" round>
                    刷新文档
                </el-button>
                <el-button icon="el-icon-full-screen" @click="toggleFullscreen" round>
                    {{ isFullscreen ? '退出全屏' : '全屏查看' }}
                </el-button>
                <el-button icon="el-icon-document" @click="showInfoDialog = true" round>
                    使用说明
                </el-button>
            </div>
        </div>

        <el-card class="swagger-card" :body-style="{ padding: '0' }">
            <div class="card-header">
                <span style="font-weight: 500; font-size: 16px;">Swagger UI</span>
                <div class="status-badge">
                    <div class="status-dot" :class="{
                        'connected': swaggerStatus === 'loaded',
                        'loading': swaggerStatus === 'loading',
                        'error': swaggerStatus === 'error'
                    }"></div>
                    <span v-if="swaggerStatus === 'loaded'">已连接</span>
                    <span v-else-if="swaggerStatus === 'loading'">加载中</span>
                    <span v-else-if="swaggerStatus === 'error'">连接失败</span>
                </div>
            </div>

            <div v-if="swaggerStatus === 'loading'" class="loading-container">
                <div class="loading-animation"></div>
                <h3 style="color: #409EFF; margin-bottom: 10px;">正在加载 API 文档</h3>
                <p class="loading-text">请稍候，我们正在从服务器获取最新的 API 文档...</p>
            </div>

            <div v-else-if="swaggerStatus === 'error'" class="error-container">
                <div class="error-icon">⚠️</div>
                <h2 class="error-title">无法加载文档</h2>
                <p class="error-desc">{{ errorMessage }}</p>
                <el-button type="primary" @click="refreshSwagger" class="refresh-btn">
                    重新加载
                </el-button>
                <el-button @click="showTroubleshooting = true" style="margin-left: 10px;">
                    故障排除
                </el-button>
            </div>
            <iframe v-show="swaggerStatus === 'loaded'" ref="swaggerFrame" :src="swaggerUrl" class="swagger-frame"
                :style="{ height: iframeHeight + 'px' }" @load="onIframeLoad" @error="onIframeError"></iframe>
        </el-card>

        <div class="footer">
            <p>Swagger UI 文档 | 最后更新: {{ lastUpdateTime }}</p>
            <div class="stats">
                <div class="stat-item">
                    <span class="stat-value">{{ connectionStatus }}</span>
                    <span class="stat-label">连接状态</span>
                </div>
                <div class="stat-item">
                    <span class="stat-value">{{ loadTime }}ms</span>
                    <span class="stat-label">加载时间</span>
                </div>
                <div class="stat-item">
                    <span class="stat-value">{{ retryCount }}</span>
                    <span class="stat-label">重试次数</span>
                </div>
            </div>
        </div>

        <!-- 使用说明对话框 -->
        <el-dialog title="Swagger UI 使用说明" :visible.sync="showInfoDialog" width="600px">
            <div class="dialog-content">
                <h3>主要功能：</h3>
                <ul>
                    <li>查看所有可用的 API 端点</li>
                    <li>测试 API 接口</li>
                    <li>查看请求和响应格式</li>
                    <li>生成 API 客户端代码</li>
                </ul>

                <h3>使用方法：</h3>
                <ol>
                    <li>点击任意 API 端点展开详细信息</li>
                    <li>点击 "Try it out" 按钮进行测试</li>
                    <li>填写请求参数并执行</li>
                    <li>查看服务器响应结果</li>
                </ol>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showInfoDialog = false">关闭</el-button>
            </span>
        </el-dialog>

        <!-- 故障排除对话框 -->
        <el-dialog title="故障排除" :visible.sync="showTroubleshooting" width="700px">
            <div class="dialog-content">
                <el-collapse v-model="activeTroubleshooting">
                    <el-collapse-item title="1. 检查服务是否运行" name="1">
                        <p>请确保 Swagger 服务正在运行在 <code>http://localhost:8082</code></p>
                        <el-button type="text" @click="testConnection">测试连接</el-button>
                    </el-collapse-item>
                    <el-collapse-item title="2. 检查网络连接" name="2">
                        <p>确认您的网络连接正常，且可以访问本地服务</p>
                    </el-collapse-item>
                    <el-collapse-item title="3. 检查防火墙设置" name="3">
                        <p>确保防火墙没有阻止对端口 8082 的访问</p>
                    </el-collapse-item>
                    <el-collapse-item title="4. 检查跨域设置" name="4">
                        <p>如果遇到跨域问题，请检查后端服务的 CORS 配置</p>
                    </el-collapse-item>
                </el-collapse>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button @click="showTroubleshooting = false">关闭</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'SwaggerViewer',
    components: { SwaggerUI },
    data() {
        return {
            swaggerUrl: 'http://localhost:7464/swagger-ui/',
            swaggerStatus: 'loading', // loading, loaded, error
            errorMessage: '',
            iframeHeight: 600,
            refreshing: false,
            lastUpdateTime: '',
            isFullscreen: false,
            showInfoDialog: false,
            showTroubleshooting: false,
            activeTroubleshooting: ['1'],
            connectionStatus: '未知',
            loadTime: 0,
            retryCount: 0,
            loadStartTime: 0
        };
    },
    mounted() {
        this.updateLastUpdateTime();
        this.loadSwagger();
        window.addEventListener('resize', this.adjustIframeHeight);
        this.adjustIframeHeight();

        // 监听全屏变化
        document.addEventListener('fullscreenchange', this.handleFullscreenChange);
    },
    beforeDestroy() {
        window.removeEventListener('resize', this.adjustIframeHeight);
        document.removeEventListener('fullscreenchange', this.handleFullscreenChange);
    },
    methods: {
        loadSwagger() {
            this.swaggerStatus = 'loading';
            this.errorMessage = '';
            this.connectionStatus = '连接中...';
            this.loadStartTime = Date.now();
        },
        onIframeLoad() {
            this.swaggerStatus = 'loaded';
            this.refreshing = false;
            this.connectionStatus = '已连接';
            this.loadTime = Date.now() - this.loadStartTime;
            this.updateLastUpdateTime();

            setTimeout(() => {
                this.adjustIframeHeight();
            }, 500);
        },
        onIframeError() {
            this.swaggerStatus = 'error';
            this.connectionStatus = '连接失败';
            this.errorMessage = '无法连接到 Swagger 文档，请检查服务是否运行在 http://localhost:8082';
            this.refreshing = false;
            this.retryCount++;
        },
        refreshSwagger() {
            this.refreshing = true;
            this.loadSwagger();
            if (this.$refs.swaggerFrame) {
                this.$refs.swaggerFrame.contentWindow.location.reload();
            }
        },
        adjustIframeHeight() {
            const windowHeight = window.innerHeight;
            const headerHeight = 120;
            const footerHeight = 100;
            const padding = 60;

            this.iframeHeight = windowHeight - headerHeight - footerHeight - padding;

            if (this.iframeHeight < 500) {
                this.iframeHeight = 500;
            }
        },
        updateLastUpdateTime() {
            const now = new Date();
            this.lastUpdateTime = now.toLocaleString();
        },
        toggleFullscreen() {
            if (!this.isFullscreen) {
                const elem = this.$el;
                if (elem.requestFullscreen) {
                    elem.requestFullscreen();
                } else if (elem.webkitRequestFullscreen) {
                    elem.webkitRequestFullscreen();
                } else if (elem.msRequestFullscreen) {
                    elem.msRequestFullscreen();
                }
            } else {
                if (document.exitFullscreen) {
                    document.exitFullscreen();
                } else if (document.webkitExitFullscreen) {
                    document.webkitExitFullscreen();
                } else if (document.msExitFullscreen) {
                    document.msExitFullscreen();
                }
            }
        },
        handleFullscreenChange() {
            this.isFullscreen = !!(
                document.fullscreenElement ||
                document.webkitFullscreenElement ||
                document.msFullscreenElement
            );
        },
        testConnection() {
            fetch(this.swaggerUrl, { method: 'HEAD', mode: 'no-cors' })
                .then(() => {
                    this.$message.success('连接测试成功！');
                })
                .catch(() => {
                    this.$message.error('连接测试失败，请检查服务状态');
                });
        }
    }
};
</script>

<style scoped>
.swagger-container {
    max-width: 1400px;
    margin: 0 auto;
    padding: 20px;
}

.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 25px;
    flex-wrap: wrap;
    gap: 15px;
}

.title-section h1 {
    font-size: 28px;
    font-weight: 600;
    color: #2c3e50;
    margin-bottom: 5px;
}

.title-section p {
    color: #7f8c8d;
    font-size: 14px;
}

.controls {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
}

.swagger-card {
    position: relative;
    min-height: 600px;
    border-radius: 12px;
    overflow: hidden;
    box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
    transition: all 0.3s ease;
}

.swagger-card:hover {
    box-shadow: 0 15px 40px rgba(0, 0, 0, 0.15);
}

.swagger-frame {
    width: 100%;
    border: none;
    background-color: white;
    transition: height 0.3s ease;
}

.loading-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 600px;
    background: white;
    border-radius: 12px;
    padding: 40px;
    text-align: center;
}

.loading-animation {
    width: 80px;
    height: 80px;
    border: 5px solid #f3f3f3;
    border-top: 5px solid #409EFF;
    border-radius: 50%;
    animation: spin 1.5s linear infinite;
    margin-bottom: 20px;
}

@keyframes spin {
    0% {
        transform: rotate(0deg);
    }

    100% {
        transform: rotate(360deg);
    }
}

.loading-text {
    font-size: 16px;
    color: #606266;
    margin-top: 10px;
}

.error-container {
    padding: 40px 20px;
    text-align: center;
    background: white;
    border-radius: 12px;
    height: 600px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.error-icon {
    font-size: 64px;
    margin-bottom: 20px;
}

.error-title {
    font-size: 24px;
    color: #F56C6C;
    margin-bottom: 10px;
}

.error-desc {
    color: #606266;
    margin-bottom: 25px;
    max-width: 500px;
    line-height: 1.6;
}

.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 18px 20px;
    background: linear-gradient(90deg, #f0f9ff 0%, #e6f7ff 100%);
    border-bottom: 1px solid #ebeef5;
}

.status-badge {
    display: flex;
    align-items: center;
    gap: 8px;
}

.status-dot {
    width: 10px;
    height: 10px;
    border-radius: 50%;
}

.status-dot.connected {
    background-color: #67C23A;
}

.status-dot.loading {
    background-color: #E6A23C;
    animation: pulse 1.5s infinite;
}

.status-dot.error {
    background-color: #F56C6C;
}

@keyframes pulse {
    0% {
        opacity: 1;
    }

    50% {
        opacity: 0.5;
    }

    100% {
        opacity: 1;
    }
}

.footer {
    margin-top: 30px;
    text-align: center;
    color: #909399;
    font-size: 14px;
    padding: 15px;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);
}

.stats {
    display: flex;
    justify-content: center;
    gap: 30px;
    margin-top: 10px;
    flex-wrap: wrap;
}

.stat-item {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.stat-value {
    font-weight: 600;
    color: #409EFF;
}

.stat-label {
    font-size: 12px;
    color: #909399;
}

.dialog-content h3 {
    margin: 15px 0 10px 0;
    color: #303133;
}

.dialog-content ul,
.dialog-content ol {
    padding-left: 20px;
    margin-bottom: 15px;
}

.dialog-content li {
    margin-bottom: 8px;
    line-height: 1.5;
}

.dialog-content code {
    background: #f4f4f5;
    padding: 2px 6px;
    border-radius: 4px;
    font-family: 'Courier New', monospace;
}

@media (max-width: 768px) {
    .header {
        flex-direction: column;
        align-items: flex-start;
    }

    .controls {
        width: 100%;
        justify-content: space-between;
    }

    .stats {
        gap: 15px;
    }

    .swagger-container {
        padding: 10px;
    }
}
</style>