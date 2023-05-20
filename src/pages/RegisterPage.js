import { useState, useEffect } from 'react';
import styled from 'styled-components';
import { FormRow, Logo  } from '../components';


const initialState = {
    name: '',
    email: '',
    password: '',
    isMember: true,
};

function Register() {
    const[values, setValues] = useState(initialState)

    const handleChange = (e) => {
        console.log(e.target)
    }

    const onSubmit = (e) => {
        e.preventDefault();
        console.log(e.target)
    }

    const toggleMember = () => {
        setValues({ ...values, isMember: !values.isMember})
    }

    return (
        <Wrapper className='full-page'>
            <form className='form' onSubmit={onSubmit}>
                <Logo />
                <h3>{values.isMember ? 'Login' : 'Register'}</h3>
                {/* UserName */}
                {!values.isMember &&
                <FormRow 
                type='text' 
                name='name' 
                value={values.name}
                handleChange={handleChange} 
                />}
                {/* email */}
                <FormRow 
                type='email' 
                name='email' 
                value={values.email}
                handleChange={handleChange} 
                />

                {/* password */}
                <FormRow 
                type='password' 
                name='password' 
                value={values.password}
                handleChange={handleChange} 
                />
                

                

                <button type='submit' className='btn btn-block'>
                    submit
                </button>

                <p>
                    {values.isMember ? " Don't have an account?" : " Already have an account?"}

                    <button type='button' onClick={toggleMember}
                    className='member-btn'>
                        {values.isMember ? 'Register' : 'Login'}
                    </button>
                </p>

            </form>
        </Wrapper>
    )


}



const Wrapper = styled.section`
    display: grid;
    align-items: center;
    .logo {
        display: block;
        margin: 0 auto;
        margin-bottom: 1.38rem;
    }
    .form {
        max-width: 400px;
        border-top: 5px solid var(--clr-primary-5);
    }

    h3 {
        text-align: center;
    }
    p {
        margin: 0;
        margin-top: 1rem;
        text-align: center;
    }
    .btn {
        margin-top: 1rem;
    }
    .member-btn {
        background: transparent;
        border: transparent;
        color: var(--primary-500);
        cursor: pointer;
        letter-spacing: var(--letterSpacing);
        margin: 0.5rem;
}
`
export default Register;