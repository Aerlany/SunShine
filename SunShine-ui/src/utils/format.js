import moment from 'moment';
//时间格式化
export function TimeFormat(data) {
    return moment(
        new Date(data)
    ).format("YYYY-MM-DD HH:mm:ss");
}

// 将字符串转化为数组
export function StringFormat(data) {
    return JSON.parse(data);
}