import request from '@/utils/request'

export function getList() {
    return request({
        url: '/project/getAll',
        method: 'get',
    })
}

export function insert(data) {
    return request({
        url: '/project/add',
        method: 'post',
        data: data
    })
}