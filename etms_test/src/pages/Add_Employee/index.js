import axios from 'axios'
import { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { toast } from 'react-toastify'
import { URL } from '../../config'



const Add_Employee = () => {

  const [name, setName] = useState('')
  const [email, setEmail] = useState('')
  const [phone, setPhone] = useState('')
  const [password, setPassword] = useState('')
  const [deptId, setDept] = useState('')
  const [designation, setDesignation] = useState('')
  const [managerId, setManagerId] = useState('')
  const [role, setRole] = useState('')

  const navigate = useNavigate()

  const save = () => {

    console.log("in save")
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
      console.log("in else block")
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

      const url = `${URL}/admin/add_employee`
      axios.post(url, body).then((response) => {
        const result = response.data

        if (result['status'] == 'success') {
          toast.success('added new employee')
          navigate('/admin_home')
        } else {
          toast.error()
        }
      })
    }

  }


  return (
    <><hr />
      <h2>Add New Employee</h2>
      <hr />
      <br />

      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-2 col-form-label">Name</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setName(e.target.value)
          }} type="text" class="form-control" id="inputEmail3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-2 col-form-label">Email</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setEmail(e.target.value)
          }} type="email" class="form-control" id="inputPassword3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-2 col-form-label">Password</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setPassword(e.target.value)
          }} type="password" class="form-control" id="inputEmail3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-2 col-form-label">Designation</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setDesignation(e.target.value)
          }} type="text" class="form-control" id="inputPassword3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputEmail3" class="col-sm-2 col-form-label">Phone</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setPhone(e.target.value)
          }} type="text" class="form-control" id="inputEmail3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-2 col-form-label">Manager ID</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setManagerId(e.target.value)
          }} type="text" class="form-control" id="inputPassword3"></input>
        </div>
      </div>
      <div class="row mb-3">
        <label for="inputPassword3" class="col-sm-2 col-form-label">Department ID</label>
        <div class="col-sm-10">
          <input onChange={(e) => {
            setDept(e.target.value)
          }} type="text" class="form-control" id="inputPassword3"></input>
        </div>
      </div>
      <div className="row">
        <div className="col">Role</div>
        <div className="col"><select onChange={(e) => {
          setRole(e.target.value)
        }} class="form-select" aria-label="Default select example">
          <option value="1">Choose Role</option>
          <option value="admin">admin</option>
          <option value="manager">manager</option>
          <option value="employee">employee</option>
        </select></div>
        <div className="col"></div>
        <div className="col"></div>
        <div className="col"></div>
        <div className="col"></div>
      </div>
      <br />
      <br />
      <div className="mb-3">
        <button onClick={save} className="btn btn-success">
          Save
          </button>
        <Link to="/admin_home" className="btn btn-danger float-end">
          Cancel
          </Link>
      </div>
    </>

  )
}

export default Add_Employee;