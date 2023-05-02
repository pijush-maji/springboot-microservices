import './Home.css';
import { useState } from "react";
import Department from "../service/Department";
import { Container, Row, Col } from "react-bootstrap";



const Home = () => {

    const [deptCd, setDeptCd] = useState('')
    const [orgCd, setOrgCd] = useState('')
    const [empCd, setEmpCd] = useState('')

    const handleDeptCdChange = (e) => {
        setDeptCd(e.target.value);
    }
    const handleOrgCdChange = (e) => {
        setOrgCd(e.target.value);
    }
    const handleEmpCdChange = (e) => {
        setEmpCd(e.target.value);
    }

    const getDepartment = (e) => {
        e.preventDefault();        
        Department.getDepartment(deptCd).then(
            (response) => {
                console.log(response)
            }
        ).catch(
            (err) => {
                console.log(err)
            }
        )
    }
    const getOrganization = (e) => {
        e.preventDefault();
        Department.getDepartment(orgCd).then(
            (response) => {
                console.log(response)
            }
        ).catch(
            (err) => {
                console.log(err)
            }
        )
    }
    const getEmployee = (e) => {
        e.preventDefault();
        Department.getDepartment(empCd).then(
            (response) => {
                console.log(response)
            }
        ).catch(
            (err) => {
                console.log(err)
            }
        )
    }

    return (
        <>
            <div className="Row">
                <Container>
                    <Row>
                        <Col className="border border-info dept">
                            <div className="m-3 p-3 ">
                                <div className="inp-box">
                                    <input type="text" id="deptCd" name="deptCd" onChange={handleDeptCdChange} />
                                    <br />
                                    <button className="btn btn-primary mt-5" onClick={getDepartment}>Get Department</button>
                                </div>
                                <div className="result-box mt-3">
                                    <div>
                                        result
                                    </div>
                                </div>
                            </div>
                        </Col>
                        <Col className="border border-info org">
                            <div className="m-3 p-3 ">
                                <div className="inp-box">
                                    <input type="text" id="orgCd" name="orgCd" onChange={handleOrgCdChange} />
                                    <br />
                                    <button className="btn btn-primary mt-5" onClick={getOrganization}>Get Organization</button>
                                </div>
                                <div className="result-box mt-3">
                                    <div>
                                        result
                                    </div>
                                </div>
                            </div>
                        </Col>
                        <Col className="border border-info emp">
                            <div className="m-3 p-3 ">
                                <div className="inp-box">
                                    <input type="text" id="empCd" name="empCd" onChange={handleEmpCdChange} />
                                    <br />
                                    <button className="btn btn-primary mt-5" onClick={getEmployee}>Get Employee</button>
                                </div>
                                <div className="result-box mt-3">
                                    <div>
                                        result
                                    </div>
                                </div>
                            </div>
                        </Col>
                        
                    </Row>
                </Container>
            </div>
        </>
    )

}

export default Home;