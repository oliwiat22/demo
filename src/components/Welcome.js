import React from 'react'
import styled from 'styled-components'
import bgImg from '../assets/about-poto.jpg'
import { Link } from 'react-router-dom'
const Welcome = () => {
    return <Wrapper className='section'>

        <div className='section-center featured'>
            <div className='form text-align:center'>   
                <h3>Unnecessary items?</h3>
                <Link to='/additem' className='btn'>
                    Sell
                </Link>
            </div>
            <div className='form '> 
                <h3>Need new items?</h3>
                <Link to='/products' className='btn'>
                    Buy
                </Link>
                
            </div>

        </div>

    </Wrapper>
}

const Wrapper = styled.section`
  h3{
    margin-bottom: 3rem;
  }
  text-align: center;  
  background-image: url(${bgImg});
  background-size: cover;
  .featured {
    margin: 4rem auto;
    display: grid;
    gap: 2.5rem;
  }
  .btn {
    display: block;
    width: 148px;
    margin: 0 auto;
    text-align: center;
  }
  p {
    line-height: 2;
    max-width: 45em;
    margin: 0 auto;
    margin-top: 2rem;
    color: var(--clr-grey-5);
  }
  .form{
    width:75%;
    border-radius: 25px;
    

  .box{}  
  }
    @media (min-width: 576px) {
    .featured {
          grid-template-columns: repeat(auto-fit, minmax(360px, 1fr));
        }
}
`


export default Welcome
