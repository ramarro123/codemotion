// Import necessary modules
import {check} from "k6";
import http from "k6/http";

export const options = {
    discardResponseBodies: true,
    tags: {
        testid:  "nonBlocking_"+__ENV.K6_TEST_ID,
    },
    scenarios: {
        nonBlocking: {
            executor: 'constant-vus',
            exec: 'test',
            vus: __ENV.K6_VUS,
            duration: __ENV.K6_TIME,
        },
    }
};

export function test() {
    // define URL and request body
    const url = "http://127.0.0.1:7070"+__ENV.K6_URL;
    const params = {
        headers: {
            "Content-Type": "application/json",
            "x-throttle": __ENV.K6_SLEEP
        }
    };

    // send a post request and save response as a variable
    const res = http.get(url, params);

    // check that response is 200
    check(res, {
        "response 200": (res) => res.status == 200,
    });
}

