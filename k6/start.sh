export K6_PROMETHEUS_RW_SERVER_URL="http://localhost:9999/api/v1/write"
export K6_PROMETHEUS_RW_TREND_AS_NATIVE_HISTOGRAM=true
export K6_PROMETHEUS_RW_STALE_MARKERS=true
export K6_PROMETHEUS_RW_PUSH_INTERVAL=1s

export K6_SLEEP=250
export K6_VUS=500
export K6_TIME="180s"

export K6_TEST_ID="$(date +%H%M)"
export K6_URL="/client/db/10"
#k6 run -o experimental-prometheus-rw server.js >server.txt 2>&1
#k6 run -o experimental-prometheus-rw block.js
k6 run -o experimental-prometheus-rw nonblock.js




