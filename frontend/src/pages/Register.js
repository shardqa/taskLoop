import React from 'react';
import { useNavigate } from 'react-router-dom';
import RegisterForm from '../components/RegisterForm';
import { useRegisterForm } from '../hooks/useRegisterForm';

const Register = () => {
  const navigate = useNavigate();
  const {
    formData,
    errors,
    isLoading,
    handleChange,
    handleSubmit
  } = useRegisterForm(navigate);

  return (
    <div className="container">
      <div className="card">
        <h2>Register</h2>
        
        <RegisterForm
          formData={formData}
          errors={errors}
          isLoading={isLoading}
          onChange={handleChange}
          onSubmit={handleSubmit}
        />
        
        <div className="mt-3">
          <p>Already have an account? <a href="/login">Login here</a></p>
        </div>
      </div>
    </div>
  );
};

export default Register; 