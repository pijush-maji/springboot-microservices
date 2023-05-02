import axios from "axios";

const baseUrl = "http://localhost:9191/api/organizations"

class Organization {

    getOrganization(code){
        return axios.get(`${baseUrl}/find/${code}`);
    }
}

export default new Organization();