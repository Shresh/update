import axios from 'axios';
export class BatteryService {
    static SERVER_URL = 'http://localhost:8080/api/battery';
    

    static createBattery(battery) {
        return axios.post(`${this.SERVER_URL}/save`, battery);
    }

    static getBatteryFromRangeBattery(batteryfilter) {
        return axios.post(`${this.SERVER_URL}/getbatteryfromrange`, batteryfilter);
    }
}