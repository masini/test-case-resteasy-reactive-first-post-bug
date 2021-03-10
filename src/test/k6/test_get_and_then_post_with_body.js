import http from 'k6/http';
import encoding from 'k6/encoding';

export let options = {
    hosts: { 'test.lab': '127.0.0.1' },
    stages: [
        { duration: '1m', target: 10 },
        { duration: '1m', target: 20 },
        { duration: '1m', target: 0 },
    ],
    thresholds: { http_req_duration: ['avg<100', 'p(95)<200'] },
    noConnectionReuse: true,
    userAgent: 'PrestoSpesa/1.0',
};


export default function () {

    const encodedCredentials = encoding.b64encode('admin:admin');

    const options = {
        headers: {
            Authorization: `Basic ${encodedCredentials}`,
        },
    };

    http.get('http://test.lab:8082/resources/test/1', options);

    var url = 'http://test.lab:8082/resources/test/2';
    var payload = JSON.stringify({
        property: 'prova'
    });

    var params = {
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Basic ${encodedCredentials}`,
        }
    };

    http.post(url, payload, params);
}
