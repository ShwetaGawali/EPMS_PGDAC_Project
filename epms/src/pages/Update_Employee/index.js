import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from "../../config";

const Update_Employee = () => {
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
  const empId = sessionStorage["toUpdateEmp"];
  const navigate = useNavigate()
  useEffect(() => {
    searchEmployees();
  }, []);

  const searchEmployees = () => {
    console.log(empId);
    const url = `${URL}/admin/employeeProfile/${empId}`;
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
        password,
        "dept": {
          deptId
        },
        designation,
        managerId,
        role,
      }

      const url = `${URL}/admin/editEmployee/${empId}`
      axios.put(url, body).then((response) => {
        const result = response.data
        if (result['status'] == 'success') {
          toast.success('updated successfully')
          navigate('/admin_home')
        } else {
          toast.error(result['error'])
        }
      })
    }

  }
  const back = () => {
    navigate('/admin_home')
  }
  return (
    <>
      <hr />
      <h2>Update Employee Details</h2>
      <hr />
      <br />

      <div class="row mb-3">
        <label htmlFor="inputEmail3" class="col-sm-2 col-form-label">
          Name
          </label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputName"
            defaultValue={name}
            onChange={e => setName(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputEmail"
            defaultValue={email}
            onChange={e => setEmail(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-2 col-form-label">
          Password
          </label>
        <div class="col-sm-10">
          <input
            type="password"
            class="form-control"
            id="inputPassword"
            defaultValue={password}
            onChange={e => setPassword(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">Designation</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputDesignation"
            defaultValue={designation}
            onChange={e => setDesignation(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-2 col-form-label">
          Phone
          </label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputPhone"
            defaultValue={phone}
            onChange={e => setPhone(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">Manager ID</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputManagerId"
            defaultValue={managerId}
            onChange={e => setManagerId(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">Department ID</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputDepartmentId"
            defaultValue={deptId}
            onChange={e => setDeptId(e.target.value)}
          ></input>
        </div>
      </div>
      <div class="row mb-3">
        <label class="col-sm-2 col-form-label">Role</label>
        <div class="col-sm-10">
          <input
            type="text"
            class="form-control"
            id="inputRole"
            defaultValue={role}
            onChange={e => setRole(e.target.value)}
          ></input>
        </div>
      </div>
      <br />
      <br />
      <div className="row">
        <div className="col"></div>
        <div className="col">
          {" "}
          <button onClick={update} type="submit" class="btn btn-primary">
            Submit
            </button>
        </div>
        <div className="col">
          {" "}
          <button onClick={back} type="submit" class="btn btn-primary">
            Back
            </button>
        </div>
      </div>

    </>
  );
};
export default Update_Employee;
