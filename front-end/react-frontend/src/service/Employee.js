import axios from "axios";

const baseUrl = "http://localhost:9191/api/employees"

class Employee {
    getEmployee(id){
        return axios.get(`${baseUrl}/find/${id}`)
    }
}

export default new Employee();