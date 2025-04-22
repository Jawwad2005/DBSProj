import React, { useState } from 'react';
import Student from './components/student.jsx';

export default function Landing() {
  // State for common form fields
  const [form, setForm] = useState({
    email: '',
    name: '',
    phone: '',
    password: '',
  });

  // Student-specific fields (cleaned)
  const [studentData, setStudentData] = useState({
    regno: '',
    memberClubs: [],
  });

  // Common input change handler
  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  // Student-specific input change handler
  const handleStudentChange = (e) => {
    const { name, value } = e.target;
    setStudentData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const requestBody = {
      email: form.email,
      password: form.password,
      name: form.name,
      phone: parseInt(form.phone),
      userType: 'STUDENT',
      regno: studentData.regno,
      memberClubs: studentData.memberClubs,
    };

    try {
      const response = await fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(requestBody),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Registration failed');
      }

      console.log('✅ Registration successful:', data);
    } catch (error) {
      console.error('❌ Registration error:', error.message);
    }
  };

  return (
    <div className="container">
      <div className="left">
        <p>Student Registration</p>
        <form onSubmit={handleSubmit}>
          <input
            type="email"
            placeholder="Email"
            name="email"
            value={form.email}
            onChange={handleChange}
            required
          />
          <input
            type="text"
            placeholder="Name"
            name="name"
            value={form.name}
            onChange={handleChange}
            required
          />
          <input
            type="tel"
            placeholder="Phone"
            name="phone"
            value={form.phone}
            onChange={handleChange}
            required
          />
          <input
            type="password"
            placeholder="Password"
            name="password"
            value={form.password}
            onChange={handleChange}
            required
          />
          <button type="submit">Register</button>
        </form>
      </div>
      <div className="right">
        <Student
          form={studentData}
          setForm={setStudentData}
          handleChange={handleStudentChange}
        />
      </div>
    </div>
  );
}
