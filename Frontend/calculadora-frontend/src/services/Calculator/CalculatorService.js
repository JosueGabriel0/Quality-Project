import axios from "axios";

const CALCULATOR_DATABASE_REST_API_URL = `${import.meta.env.VITE_API_BASE_URL}/calculadora`;

class CalculatorService{
    postCalcular(numbers){
        return(
            axios.post(`${CALCULATOR_DATABASE_REST_API_URL}/calcular`, numbers)
        );
    }

    getAllCalculatorHistory(){
        return(
            axios.get(`${CALCULATOR_DATABASE_REST_API_URL}/historial`)
        );
    }
}

export default new CalculatorService();