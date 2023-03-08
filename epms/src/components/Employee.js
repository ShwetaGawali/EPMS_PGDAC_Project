import { useLocation, Link, useNavigate } from 'react-router'
import axios from 'axios'
import { useState } from 'react'

import { toast } from 'react-toastify'
import { URL } from '../config'
const Employee = (props) => {

    const { empId, name, email, phone, password, dept, managerId } = props.employee
    const navigate = useNavigate()
    const emp = sessionStorage['EmpId']

    const updateEmployee = () => {
        sessionStorage["toUpdateEmp"] = empId
        navigate('/update_employee')
    }
    const deleteEmployee = () => {
        const url = `${URL}/deleteEmployee/${empId}`;
        axios.delete(url).then((response) => {
            const result = response.data;
            window.location.reload()
            toast.success(' employee record deleted')

        });
        navigate('/admin_home')
    }
    return (

        <tr>
            <td>{empId}</td>
            <td>{name}</td>
            <td>{email}</td>
            <td>{phone}</td>
            <td>{dept.deptId}</td>
            <td>{managerId}</td>
            <td>
                <div className="mb-3">
                    <button onClick={updateEmployee} className="btn btn-primary">Update</button>
                </div>
            </td>
            <td>
                {emp != empId && (
                    <div className="mb-3">
                        <button onClick={deleteEmployee} className="btn btn-primary">Delete</button>
                    </div>
                )}
            </td>
        </tr>

    )
}

export default Employee