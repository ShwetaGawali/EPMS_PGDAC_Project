import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from "../../config";

const Employee_Profile = () => {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [phone, setPhone] = useState("");
  const [password, setPassword] = useState("");
  const [dept, setDept] = useState("");
  const [deptId, setDeptId] = useState("");
  const [designation, setDesignation] = useState("");
  const [managerId, setManagerId] = useState("");
  const [role, setRole] = useState("");
  // const [employee, setEmployees] = useState([])
  const empId = sessionStorage["EmpId"];
  const navigate = useNavigate()
  useEffect(() => {
    searchEmployees();
  }, []);

  const searchEmployees = () => {
    console.log(empId);
    const url = `${URL}/employeeProfile/${empId}`;
    axios.get(url).then((response) => {
      const result = response.data;

      console.log(result);
      setName(result.data.name);
      setEmail(result.data.email);
      setPassword(result.data.password);
      setPhone(result.data.phone);
      setDeptId(result.data.dept.deptId);
      setDesignation(result.data.designation);
      setManagerId(result.data.managerId);
      setRole(result.data.role);
      //    if (result['status'] == 'success') {
      //        setEmployees(result['data'])
      //    } else {
      //        toast.error(result['error'])
      //    }
    });

    console.log(name);
  }
  const update = () => {
    let body = {}
    if (name.length == 0) {
      toast.warning('please enter name')
      console.log("1")
    } else if (email.length == 0) {
      toast.warning('please enter email id')
      console.log("2")
    } else if (phone.length == 0) {
      toast.warning('please enter mobile number')
      console.log("3")
    } else if (password.length == 0) {
      toast.warning('please enter password')
      console.log("4")
    } else if (deptId.length == 0) {
      toast.warning('please enter department Id')
      console.log("5")
    } else if (designation.length == 0) {
      toast.warning('please enter designation')
      console.log("6")
    } else if (role.length == 0) {
      toast.warning('please enter role')
      console.log("7")
    }
    else {

      body = {
        name,
        email,
        phone,

      }




      const url = `${URL}/employeeUpdate/${empId}`
      axios.patch(url, body).then((response) => {
        const result = response.data
        if (result['status'] == 'success') {
          sessionStorage["Name"] = result.data.name
          toast.success('Employee Profile Updated..')

          if (sessionStorage["Role"] == "employee") { navigate('/employee_home') }
          else if (sessionStorage["Role"] == "manager") { navigate('/managerHome') }
          else if (sessionStorage["Role"] == "admin") { navigate('/admin_home') }
        } else {
          toast.error(result['error'])
        }
      }).catch((error) => (console.log("Error" + error)))
    }

  }
  const back = () => {
    if (sessionStorage["Role"] == "employee") { navigate('/employee_home') }
    else if (sessionStorage["Role"] == "manager") { navigate('/managerHome') }
    else if (sessionStorage["Role"] == "admin") { navigate('/admin_home') }
  }



  return (
    <div>
      <div className="row">
        <div className="col">
          <h2>Employee Profile</h2>
        </div>
      </div>


      <div class="row mb-3">
        <label htmlFor="colFormLabel" class="col-sm-2 col-form-label"><h5>Name</h5></label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputName"
            defaultValue={name}
            onChange={e => setName(e.target.value)}>
          </input>
        </div>
      </div><div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Email</h5></label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputEmail"
            defaultValue={email}
            onChange={e => setEmail(e.target.value)}
          >
          </input>
        </div>
      </div>



      <div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Phone</h5></label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputPhone"
            defaultValue={phone}
            onChange={e => setPhone(e.target.value)}
          >
          </input>
        </div>
      </div><div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Role</h5></label>
        <div class="col-sm-10">
          <input class="form-control" type="text"
            defaultValue={role}
            onChange={e => setRole(e.target.value)}

            aria-label="Disabled input example" disabled readonly>
          </input>
        </div>
      </div><div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Designation</h5></label>
        <div class="col-sm-10">
          <input class="form-control" type="text"
            defaultValue={designation}
            onChange={e => setDesignation(e.target.value)}

            aria-label="Disabled input example" disabled readonly>
          </input>
        </div>
      </div><div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Dept ID</h5></label>
        <div class="col-sm-10">
          <input class="form-control" type="text"
            defaultValue={deptId}
            onChange={e => setDeptId(e.target.value)}

            aria-label="Disabled input example" disabled readonly>
          </input>
        </div>
      </div><div class="row mb-3">
        <label for="colFormLabel" class="col-sm-2 col-form-label"><h5>Manager ID</h5></label>
        <div class="col-sm-10">
          <input class="form-control" type="text"
            defaultValue={managerId}
            onChange={e => setManagerId(e.target.value)}

            aria-label="Disabled input example" disabled readonly>
          </input>
        </div>
        <hr />

      </div>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <button onClick={update} type="button" class="btn btn-primary" >Save</button>

        </div>
        <div className="col"><button onClick={back} type="button" class="btn btn-primary">Back</button></div>
      </div>


    </div>

  )
}

export default Employee_Profile