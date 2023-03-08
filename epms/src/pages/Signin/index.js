import './index.css'
import { useState } from 'react'
import { toast } from 'react-toastify'
import axios from 'axios'
import { useNavigate } from 'react-router'
import { URL } from '../../config'
//import { analytics } from '../../firebaseConfig'


const Signin = () => {
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const navigate = useNavigate()

    const signinUser = () => {
        if (email.length == 0) {
            toast.warning('please enter email')
        } else if (password.length == 0) {
            toast.warning('please enter password')
        } else {
            const body = {
                email,
                password,
            }
            // url to make signin api call
            const url = `${URL}/employee/signin`

            // make api call using axios
            axios.post(url, body).then((response) => {
                // get the server result
                const result = response.data
                console.log(result)
                if (result['status'] == 'success') {
                    //analytics.logEvent("SignIn_Success")
                    toast.success('Welcome to the application')

                    // get the data sent by server
                    const { empId, name, dept, role } = result['data']

                    // persist the logged in user's information for future use
                    sessionStorage['EmpId'] = empId
                    sessionStorage['Name'] = name
                    sessionStorage['DeptId'] = dept.deptId
                    sessionStorage['loginStatus'] = 1
                    sessionStorage['Role'] = role

                    if (role == 'admin')
                        navigate('/admin_home')
                    else if (role == 'manager')
                        navigate('/managerHome')
                    else
                        navigate('/employee_home')
                } else {
                    toast.error('Invalid user name or password')
                }
            })
        }
    }

    return (
        <div>
            
            <br /><br />
            <h2>Signin</h2>
            <hr />
            <div className="row" >
                <div className="col"></div>
                <div className="col">
                    <div className="form">
                        <div className="mb-3">
                            <label htmlFor="" className="label-control">Email address</label>
                            <input onChange={(e) => {
                                setEmail(e.target.value)
                            }} type="text" className="form-control" />
                        </div>
                        <div className="mb-3">
                            <label htmlFor="" className="label-control">Password</label>
                            <input onChange={(e) => {
                                setPassword(e.target.value)
                            }} type="password" className="form-control" />
                        </div>
                        <div id="button" className="mb-3">
                            <button onClick={signinUser} className="btn btn-primary">Login</button>
                        </div>
                    </div>
                </div>
                <div className="col"></div>
            </div>
        </div>
    )
}


export default Signin