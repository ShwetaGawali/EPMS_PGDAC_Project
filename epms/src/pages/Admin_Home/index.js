import { useLocation, useNavigate } from 'react-router'
import { useEffect, useState } from 'react'
import { toast } from 'react-toastify'
import Employee from '../../components/Employee'
import { URL } from '../../config'

import axios from 'axios'
const Admin_Home = () => {
  const [employee, setEmployees] = useState([])
  const id = sessionStorage['empId']
  const Name = sessionStorage['Name']

  const searchEmployees = () => {
    const url = `${URL}/admin/list_employees`
    axios.get(url).then((response) => {
      const result = response.data
      if (result['status'] == 'success') {
        setEmployees(result['data'])
      } else {
        toast.error(result['error'])
      }
    })
  }



  const navigate = useNavigate()

  const logoutUser = () => {
    // remove the logged users details from session storage
    sessionStorage.removeItem('empId')
    sessionStorage.removeItem('name')
    sessionStorage.removeItem('deptId')
    sessionStorage.removeItem('loginStatus')

    navigate('/signin')
  }
  const addEmployee = () => {

    navigate('/add_employee')

  }
  const employeeProfile = () => {
    navigate('/employee_profile')
  }
  const changePassword = () => {
    navigate('/change_password')
  }
  useEffect(() => {
    searchEmployees()
  }, [])


  return (
    <>
      <hr />


      <div className="row">

        <div className="col">
          <h3>Admin Home Page</h3>
        </div>
        <div className="col"></div>
        <div className="col"></div>
        <div className="col">

          <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              Welcome,{Name}
            </button>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" ><button onClick={employeeProfile} className="dropdown-item">
                My Profile</button></a></li>
              <li><a class="dropdown-item" ><button onClick={changePassword} className="dropdown-item">
                Change Password</button></a></li>
              <li><hr class="dropdown-divider" /></li>
              <li><a class="dropdown-item">
                <button onClick={logoutUser} className="dropdown-item">
                  Logout
                </button></a></li>
            </ul>
          </div>

        </div>
        <br />
        <br />
        <hr />
        <br />
       
        <div className="row">
          <div className="col"></div>
          <div className="col"></div>
          <div className="col-md-auto"><button onClick={addEmployee} type="button" class="btn btn-primary"><h5>Add New Employee</h5></button></div>
          <div className="col"></div>
          <div className="col"></div>
        </div>
      </div>
      <br />
      <br />
      <div>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Name</th>
              <th scope="col">Email</th>
              <th scope="col">Phone</th>
              <th scope="col"> Department ID</th>
              <th scope="col"> Manager ID</th>
              <th scope="col"> Action</th>
            </tr>
          </thead>
          <tbody>
            
            {employee.map((tempEmployee) => {
              return <Employee employee={tempEmployee} />
            })}


          </tbody>
        </table>
      </div>
    </>
  );
};

export default Admin_Home
