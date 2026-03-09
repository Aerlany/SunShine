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
                  <el-input type="textarea" :rows="4" v-model="supportForm.message"
                     placeholder="请详细描述您遇到的问题"></el-input>
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
      navigateTo(path) {
         this.$router.push(path);
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
         const elements = document.querySelectorAll('.error-code, .error-message, .action-buttons');
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
   width: 100px;
   height: 100px;
   top: 10%;
   left: 10%;
   animation-delay: 0s;
}

.shape-2 {
   width: 150px;
   height: 150px;
   top: 60%;
   right: 10%;
   animation-delay: 5s;
}

.shape-3 {
   width: 80px;
   height: 80px;
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
      transform: translateY(-20px) rotate(120deg);
   }

   66% {
      transform: translateY(20px) rotate(240deg);
   }
}

.content-wrapper {
   position: relative;
   z-index: 1;
   width: 100%;
   max-width: 800px;
   padding: 20px;
}

.error-card {
   border-radius: 16px;
   overflow: hidden;
   background: rgba(255, 255, 255, 0.95);
   backdrop-filter: blur(10px);
   border: none;
}

.error-content {
   padding: 40px 30px;
   text-align: center;
}

.error-code {
   display: flex;
   justify-content: center;
   align-items: center;
   margin-bottom: 30px;
   font-size: 0;
   /* 防止空格影响布局 */
}

.digit {
   font-size: 120px;
   font-weight: 800;
   color: #f56c6c;
   text-shadow: 3px 3px 0 rgba(0, 0, 0, 0.1);
   line-height: 1;
}

.zero-container {
   position: relative;
   width: 120px;
   height: 120px;
   margin: 0 20px;
   display: flex;
   align-items: center;
   justify-content: center;
}

.zero-circle {
   width: 100px;
   height: 100px;
   border: 10px solid #f56c6c;
   border-radius: 50%;
   position: relative;
   animation: pulse 2s infinite;
}

.zero-circle::after {
   content: '';
   position: absolute;
   top: 50%;
   left: 50%;
   transform: translate(-50%, -50%);
   width: 60px;
   height: 60px;
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
      transform: scale(1.05);
      opacity: 0.8;
   }
}

.error-message h1 {
   font-size: 32px;
   color: #303133;
   margin-bottom: 15px;
   font-weight: 600;
}

.error-message p {
   font-size: 18px;
   color: #606266;
   margin-bottom: 25px;
   line-height: 1.6;
}

.error-details {
   background: #f8f9fa;
   border-radius: 8px;
   padding: 15px;
   margin: 20px 0;
   text-align: left;
   max-width: 400px;
   margin-left: auto;
   margin-right: auto;
}

.error-details p {
   font-weight: 500;
   margin-bottom: 10px;
   color: #409EFF;
}

.error-details ul {
   text-align: left;
   padding-left: 20px;
   margin: 0;
}

.error-details li {
   margin-bottom: 8px;
   color: #606266;
}

.action-buttons {
   margin: 30px 0;
   display: flex;
   justify-content: center;
   gap: 15px;
   flex-wrap: wrap;
}

.home-btn {
   background: linear-gradient(135deg, #409EFF 0%, #67C23A 100%);
   border: none;
   box-shadow: 0 4px 15px rgba(64, 158, 255, 0.3);
}

.home-btn:hover {
   transform: translateY(-2px);
   box-shadow: 0 6px 20px rgba(64, 158, 255, 0.4);
}

.quick-links h3 {
   color: #303133;
   margin-bottom: 20px;
   font-weight: 500;
}




.link-card:hover {
   background: #ecf5ff;
   border-color: #409EFF;
   transform: translateY(-3px);
   box-shadow: 0 5px 15px rgba(64, 158, 255, 0.1);
}

.link-card i {
   font-size: 24px;
   color: #409EFF;
   margin-bottom: 10px;
}

.link-card span {
   color: #606266;
   font-size: 14px;
}

.support-section {
   margin-top: 30px;
   padding-top: 20px;
   border-top: 1px solid #ebeef5;
}

.support-section p {
   color: #909399;
   margin-bottom: 10px;
}

.support-dialog {
   padding: 10px 0;
}

.contact-info {
   margin-top: 20px;
   padding: 15px;
   background: #f8f9fa;
   border-radius: 8px;
}

.contact-info h4 {
   margin-bottom: 10px;
   color: #409EFF;
}

.contact-info p {
   margin: 5px 0;
   color: #606266;
}

.footer {
   margin-top: 30px;
   text-align: center;
   color: rgba(255, 255, 255, 0.7);
   font-size: 14px;
}

/* 响应式设计 */
@media (max-width: 768px) {
   .content-wrapper {
      padding: 10px;
   }

   .error-content {
      padding: 30px 20px;
   }

   .digit {
      font-size: 80px;
   }

   .zero-container {
      width: 80px;
      height: 80px;
      margin: 0 10px;
   }

   .zero-circle {
      width: 70px;
      height: 70px;
   }

   .zero-circle::after {
      width: 40px;
      height: 40px;
   }

   .error-message h1 {
      font-size: 24px;
   }

   .error-message p {
      font-size: 16px;
   }

   .action-buttons {
      flex-direction: column;
      align-items: center;
   }

   .action-buttons .el-button {
      width: 100%;
      max-width: 250px;
   }

   .link-grid {
      grid-template-columns: repeat(2, 1fr);
   }
}

@media (max-width: 480px) {
   .digit {
      font-size: 60px;
   }

   .zero-container {
      width: 60px;
      height: 60px;
      margin: 0 5px;
   }

   .zero-circle {
      width: 50px;
      height: 50px;
      border-width: 6px;
   }

   .zero-circle::after {
      width: 30px;
      height: 30px;
   }

   .link-grid {
      grid-template-columns: 1fr;
   }
}
</style>