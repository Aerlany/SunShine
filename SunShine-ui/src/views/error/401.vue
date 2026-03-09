<template>
  <div class="not-found-container">
    <div class="background-animation">
      <div class="floating-shape shape-1"></div>
      <div class="floating-shape shape-2"></div>
      <div class="floating-shape shape-3"></div>
    </div>

    <div class="content-wrapper">
      <el-card class="error-card" shadow="hover">
        <div class="error-content">
          <!-- 错误代码展示 -->
          <div class="error-code">
            <span class="digit">4</span>
            <div class="zero-container">
              <div class="zero-circle"></div>
            </div>
            <span class="digit">4</span>
          </div>

          <!-- 错误信息 -->
          <div class="error-message">
            <h1>页面未找到</h1>
            <p>抱歉，您访问的页面不存在或已被移动</p>
            <div class="error-details">
              <p>可能的原因：</p>
              <ul>
                <li>URL地址拼写错误</li>
                <li>页面已被删除或移动</li>
                <li>链接已过期</li>
              </ul>
            </div>
          </div>

          <!-- 操作按钮 -->
          <div class="action-buttons">
            <el-button type="primary" icon="el-icon-house" @click="goHome" size="large" class="home-btn">
              返回首页
            </el-button>
            <el-button icon="el-icon-back" @click="goBack" size="large">
              返回上页
            </el-button>
            <el-button icon="el-icon-refresh" @click="reloadPage" size="large">
              刷新页面
            </el-button>
          </div>

          <!-- 快速导航 -->
          <div class="quick-links">
            <h3>快速导航</h3>
            <div class="link-grid">
              <div class="link-card" @click="goHome">
                <i class="el-icon-house"></i>
                <span>首页</span>
              </div>
              <div class="link-card" @click="$router.push('/swagger')">
                <i class="el-icon-document"></i>
                <span>API文档</span>
              </div>
              <div class="link-card" @click="$router.push('/guide')">
                <i class="el-icon-notebook-2"></i>
                <span>使用指南</span>
              </div>
              <div class="link-card" @click="$router.push('/about')">
                <i class="el-icon-info"></i>
                <span>关于我们</span>
              </div>
            </div>
          </div>

          <!-- 技术支持 -->
          <div class="support-section">
            <p>如果问题持续存在，请联系技术支持</p>
            <el-button type="text" icon="el-icon-service" @click="contactSupport">
              联系技术支持
            </el-button>
          </div>
        </div>
      </el-card>

      <!-- 页面底部 -->
      <div class="footer">
        <p>&copy; {{ currentYear }} 您的公司名称. 保留所有权利.</p>
      </div>
    </div>

    <!-- 技术支持对话框 -->
    <el-dialog title="联系技术支持" :visible.sync="showSupportDialog" width="500px" center>
      <div class="support-dialog">
        <el-form :model="supportForm" label-width="80px">
          <el-form-item label="您的邮箱">
            <el-input v-model="supportForm.email" placeholder="请输入您的邮箱地址"></el-input>
          </el-form-item>
          <el-form-item label="问题描述">
            <el-input type="textarea" :rows="4" v-model="supportForm.message" placeholder="请详细描述您遇到的问题"></el-input>
          </el-form-item>
        </el-form>
        <div class="contact-info">
          <h4>其他联系方式：</h4>
          <p>📧 support@yourcompany.com</p>
          <p>📞 400-123-4567</p>
        </div>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showSupportDialog = false">取消</el-button>
        <el-button type="primary" @click="submitSupportRequest">提交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'NotFound',
  data() {
    return {
      showSupportDialog: false,
      supportForm: {
        email: '',
        message: ''
      },
      currentYear: new Date().getFullYear(),
    };
  },
  mounted() {
    // 添加页面进入动画
    this.animatePageEntry();
  },
  methods: {
    goHome() {
      this.$router.push('/');
    },
    goBack() {
      this.$router.go(-1);
    },
    reloadPage() {
      window.location.reload();
    },
    contactSupport() {
      this.showSupportDialog = true;
    },
    submitSupportRequest() {
      if (!this.supportForm.email || !this.supportForm.message) {
        this.$message.warning('请填写邮箱和问题描述');
        return;
      }

      // 模拟提交
      this.$message.success('支持请求已提交，我们将尽快回复您');
      this.showSupportDialog = false;
      this.supportForm.email = '';
      this.supportForm.message = '';
    },
    animatePageEntry() {
      // 页面进入动画
      const elements = document.querySelectorAll('.error-code, .error-message, .action-buttons, .quick-links');
      elements.forEach((el, index) => {
        el.style.opacity = '0';
        el.style.transform = 'translateY(20px)';

        setTimeout(() => {
          el.style.transition = 'all 0.6s ease';
          el.style.opacity = '1';
          el.style.transform = 'translateY(0)';
        }, index * 200);
      });
    }
  }
};
</script>

<style scoped>
.not-found-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  overflow: hidden;
  font-family: 'Helvetica Neue', Arial, 'PingFang SC', 'Microsoft YaHei', sans-serif;
  padding: 40px 20px;
}

.background-animation {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 0;
}

.floating-shape {
  position: absolute;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.1);
  animation: float 15s infinite ease-in-out;
}

.shape-1 {
  width: 120px;
  height: 120px;
  top: 15%;
  left: 10%;
  animation-delay: 0s;
}

.shape-2 {
  width: 180px;
  height: 180px;
  top: 60%;
  right: 10%;
  animation-delay: 5s;
}

.shape-3 {
  width: 100px;
  height: 100px;
  bottom: 20%;
  left: 20%;
  animation-delay: 10s;
}

@keyframes float {

  0%,
  100% {
    transform: translateY(0) rotate(0deg);
  }

  33% {
    transform: translateY(-25px) rotate(120deg);
  }

  66% {
    transform: translateY(25px) rotate(240deg);
  }
}

.content-wrapper {
  position: relative;
  z-index: 1;
  width: 100%;
  max-width: 900px;
  margin: 0 auto;
}

.error-card {
  border-radius: 20px;
  overflow: hidden;
  background: rgba(255, 255, 255, 0.95);
  backdrop-filter: blur(15px);
  border: none;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.error-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 25px 50px rgba(0, 0, 0, 0.15);
}

.error-content {
  padding: 60px 50px;
  text-align: center;
}

.error-code {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 40px;
  font-size: 0;
}

.digit {
  font-size: 140px;
  font-weight: 900;
  color: #f56c6c;
  text-shadow: 4px 4px 0 rgba(0, 0, 0, 0.1);
  line-height: 1;
  transition: transform 0.3s ease;
}

.digit:hover {
  transform: scale(1.05);
}

.zero-container {
  position: relative;
  width: 140px;
  height: 140px;
  margin: 0 25px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.zero-circle {
  width: 120px;
  height: 120px;
  border: 12px solid #f56c6c;
  border-radius: 50%;
  position: relative;
  animation: pulse 2s infinite ease-in-out;
  transition: all 0.3s ease;
}

.zero-circle:hover {
  border-color: #e6a23c;
  transform: rotate(15deg);
}

.zero-circle::after {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 70px;
  height: 70px;
  background: white;
  border-radius: 50%;
}

@keyframes pulse {

  0%,
  100% {
    transform: scale(1);
    opacity: 1;
  }

  50% {
    transform: scale(1.08);
    opacity: 0.9;
  }
}

.error-message h1 {
  font-size: 36px;
  color: #303133;
  margin-bottom: 20px;
  font-weight: 700;
}

.error-message p {
  font-size: 20px;
  color: #606266;
  margin-bottom: 30px;
  line-height: 1.6;
}

.error-details {
  background: #f8f9fa;
  border-radius: 12px;
  padding: 20px;
  margin: 30px auto;
  text-align: left;
  max-width: 450px;
  border-left: 4px solid #409EFF;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.error-details p {
  font-weight: 600;
  margin-bottom: 12px;
  color: #409EFF;
  font-size: 16px;
}

.error-details ul {
  text-align: left;
  padding-left: 20px;
  margin: 0;
}

.error-details li {
  margin-bottom: 10px;
  color: #606266;
  font-size: 15px;
  position: relative;
}

.error-details li::before {
  content: "•";
  color: #409EFF;
  font-weight: bold;
  position: absolute;
  left: -15px;
}

.action-buttons {
  margin: 40px 0;
  display: flex;
  justify-content: center;
  gap: 20px;
  flex-wrap: wrap;
}

.home-btn {
  background: linear-gradient(135deg, #409EFF 0%, #67C23A 100%);
  border: none;
  box-shadow: 0 6px 20px rgba(64, 158, 255, 0.3);
  transition: all 0.3s ease;
  padding: 15px 30px;
  font-size: 16px;
  font-weight: 500;
}

.home-btn:hover {
  transform: translateY(-3px);
  box-shadow: 0 8px 25px rgba(64, 158, 255, 0.4);
}

.action-buttons .el-button:not(.home-btn) {
  padding: 15px 30px;
  font-size: 16px;
  transition: all 0.3s ease;
}

.action-buttons .el-button:not(.home-btn):hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
}

.quick-links {
  margin: 40px 0 30px;
  padding-top: 30px;
  border-top: 1px solid #ebeef5;
}

.quick-links h3 {
  color: #303133;
  margin-bottom: 25px;
  font-weight: 600;
  font-size: 20px;
}

.link-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 20px;
  max-width: 600px;
  margin: 0 auto;
}

.link-card {
  background: #f5f7fa;
  border-radius: 12px;
  padding: 20px 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  cursor: pointer;
  transition: all 0.3s ease;
  border: 2px solid transparent;
  text-align: center;
}

.link-card:hover {
  background: #ecf5ff;
  border-color: #409EFF;
  transform: translateY(-5px);
  box-shadow: 0 8px 25px rgba(64, 158, 255, 0.15);
}

.link-card i {
  font-size: 28px;
  color: #409EFF;
  margin-bottom: 12px;
  transition: transform 0.3s ease;
}

.link-card:hover i {
  transform: scale(1.2);
}

.link-card span {
  color: #606266;
  font-size: 14px;
  font-weight: 500;
}

.support-section {
  margin-top: 30px;
  padding-top: 25px;
  border-top: 1px solid #ebeef5;
}

.support-section p {
  color: #909399;
  margin-bottom: 15px;
  font-size: 15px;
}

.support-section .el-button {
  font-size: 15px;
  font-weight: 500;
}

.support-dialog {
  padding: 10px 0;
}

.contact-info {
  margin-top: 20px;
  padding: 20px;
  background: #f8f9fa;
  border-radius: 10px;
  border-left: 4px solid #409EFF;
}

.contact-info h4 {
  margin-bottom: 12px;
  color: #409EFF;
  font-size: 16px;
}

.contact-info p {
  margin: 8px 0;
  color: #606266;
  font-size: 14px;
}

.footer {
  margin-top: 40px;
  text-align: center;
  color: rgba(255, 255, 255, 0.8);
  font-size: 14px;
  padding: 20px;
}

/* 电脑端专属优化 */
@media (min-width: 1024px) {
  .error-content {
    padding: 70px 60px;
  }

  .digit {
    font-size: 160px;
  }

  .zero-container {
    width: 160px;
    height: 160px;
    margin: 0 30px;
  }

  .zero-circle {
    width: 140px;
    height: 140px;
  }

  .zero-circle::after {
    width: 80px;
    height: 80px;
  }

  .error-message h1 {
    font-size: 42px;
  }

  .error-message p {
    font-size: 22px;
  }

  .link-grid {
    grid-template-columns: repeat(4, 1fr);
    max-width: 700px;
  }

  .link-card {
    padding: 25px 20px;
  }
}

/* 平板端适配 */
@media (max-width: 1023px) and (min-width: 768px) {
  .content-wrapper {
    max-width: 700px;
  }

  .link-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 15px;
  }
}

/* 移动端适配 */
@media (max-width: 767px) {
  .not-found-container {
    padding: 20px 15px;
  }

  .content-wrapper {
    padding: 0;
  }

  .error-content {
    padding: 40px 25px;
  }

  .digit {
    font-size: 100px;
  }

  .zero-container {
    width: 100px;
    height: 100px;
    margin: 0 15px;
  }

  .zero-circle {
    width: 80px;
    height: 80px;
    border-width: 8px;
  }

  .zero-circle::after {
    width: 50px;
    height: 50px;
  }

  .error-message h1 {
    font-size: 28px;
  }

  .error-message p {
    font-size: 16px;
  }

  .action-buttons {
    flex-direction: column;
    align-items: center;
    gap: 15px;
  }

  .action-buttons .el-button {
    width: 100%;
    max-width: 280px;
  }

  .link-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 12px;
  }

  .link-card {
    padding: 15px 10px;
  }
}

@media (max-width: 480px) {
  .digit {
    font-size: 80px;
  }

  .zero-container {
    width: 80px;
    height: 80px;
    margin: 0 10px;
  }

  .zero-circle {
    width: 65px;
    height: 65px;
    border-width: 6px;
  }

  .zero-circle::after {
    width: 40px;
    height: 40px;
  }

  .link-grid {
    grid-template-columns: 1fr;
  }
}
</style>