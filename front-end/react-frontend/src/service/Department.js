import axios from "axios";

const baseUrl = "http://localhost:9191/api/departments";

class Department {

    getDepartment(code) {
        return axios.get(`${baseUrl}/find/${code}`);
    }
}

export default new Department();