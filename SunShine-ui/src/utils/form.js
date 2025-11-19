// 统一校验表单
export function validateForm(DOM, callback) {
    DOM.validate((valid) => {
        if (valid) {
            callback();
        }
        else {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
            return false;
        }
    });
}