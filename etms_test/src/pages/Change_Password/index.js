import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from "../../config";

const Change_Password = () => {
    const empId = sessionStorage['EmpId']

    console.log(empId)
    const navigate = useNavigate()
    useEffect(() => {
        searchEmployees();
    }, []);

    const [oldPassword, setOldPassword] = useState("");
    const [password, setPassword] = useState("");
    const searchEmployees = () => {
        console.log(empId);
        const url = `${URL}/admin/employeeProfile/${empId}`;
        axios.get(url).then((response) => {
            const result = response.data;
            setOldPassword(result.data.password);
        });
    }
    const updatePassword = () => {
        let body = {}
        if (password.length == 0) {
            toast.warning('please enter password')
            console.log("4")
        }
        else {

            body = {

                password,

            }

            const url = `${URL}/change_password/${empId}`
            axios.patch(url, body).then((response) => {
                const result = response.data

                if (result['status'] == 'success') {
                    if (sessionStorage["Role"] == "employee") { navigate('/employee_home') }
                    else if (sessionStorage["Role"] == "manager") { navigate('/managerHome') }
                    else if (sessionStorage["Role"] == "admin") { navigate('/admin_home') }
                    toast.success('password changed')
                } else {
                    toast.error()
                }
            })
        }
    }
    return (
        <><hr /><><h2>Change Password</h2><hr />

            <div className="row g-3 align-items-center">
                <div className="col"></div>
                <div className="col-auto">
                    <label for="inputPassword6" className="col-form-label">Old Password</label>
                </div>
                <div class="col-auto">
                    <input type="password" id="inputPassword5" defaultValue={oldPassword}
                        class="form-control" aria-describedby="passwordHelpInline">
                    </input></div>
                <div className="col-auto">

                </div>
                <div className="col"></div>
            </div>
            <br />
            <div className="row g-3 align-items-center">
                <div className="col"></div>
                <div className="col-auto">
                    <label for="inputPassword6" className="col-form-label">New Password</label>
                </div>
                <div class="col-auto">
                    <input type="password" onChange={e => setPassword(e.target.value)} id="inputPassword6" class="form-control" aria-describedby="passwordHelpInline">
                    </input></div>
                <div className="col-auto">

                </div>
                <div className="col"></div>
            </div>
            <br />

            <div className="row">
                <div className="col"></div>
                <div className="col"> <button onClick={updatePassword} class="btn btn-primary">Submit</button></div>
            </div>

        </></>
    )
}

export default Change_Password