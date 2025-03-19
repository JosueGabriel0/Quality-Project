import { useEffect, useState } from "react";
import styles from "./Calculator.module.css";
import CalculatorService from "../../services/Calculator/CalculatorService";

const Calculator = () => {
    const [input, setInput] = useState("");
    const [historiales, setHistoriales] = useState([]);
    const [errorMessage, setErrorMessage] = useState(""); // Estado para manejar errores

    // Maneja la entrada del usuario
    const handleInput = (value) => {
        setInput((prev) => prev + value);
        setErrorMessage(""); // Resetear errores al escribir
    };

    const calculateResult = () => {
        try {
            // Expresión regular para extraer número1, operador y número2
            const regex = /(\d+)\s*([\+\-\*\/])\s*(\d+)/;
            const match = input.match(regex);

            if (!match) {
                throw new Error("Formato inválido. Usa el formato: número operador número");
            }

            // Extraer valores
            let num1 = parseFloat(match[1]);
            let num2 = parseFloat(match[3]);
            let operacion = match[2];

            // Convertir el operador a su equivalente en texto
            const operacionesMap = {
                "+": "suma",
                "-": "resta",
                "*": "multiplicacion",
                "/": "division",
            };

            operacion = operacionesMap[operacion] || null;

            if (!operacion) {
                throw new Error("Operación inválida. Usa +, -, * o /");
            }

            // Validar división por 0
            if (operacion === "division" && num2 === 0) {
                throw new Error("Error: No se puede dividir por 0");
            }

            // Construir objeto con los datos extraídos
            const numbers = { num1, num2, operacion };

            // Llamar al servicio para calcular
            CalculatorService.postCalcular(numbers)
                .then((response) => {
                    setInput(response.data);
                    setErrorMessage(""); // Limpiar errores al obtener una respuesta válida
                })
                .catch((error) => {
                    throw new Error("Error en el cálculo. Intenta de nuevo.");
                });

        } catch (error) {
            setErrorMessage(error.message);
            console.error(error);
        }
    };

    // Borra la pantalla
    const clearInput = () => {
        setInput("");
        setErrorMessage("");
    };

    const obtenerHistorial = () => {
        CalculatorService.getAllCalculatorHistory()
            .then((response) => {
                setHistoriales(response.data);
            })
            .catch((error) => {
                console.error("Error obteniendo el historial:", error);
            });
    };

    function historialResponse(operacion, resultado) {
        const operacionesMap = {
            "suma": "+",
            "resta": "-",
            "multiplicacion": "*",
            "division": "/",
        };

        let operacionFormateada = operacion;
        Object.keys(operacionesMap).forEach((key) => {
            operacionFormateada = operacionFormateada.replace(key, operacionesMap[key]);
        });

        return `${operacionFormateada} = ${resultado}`;
    }

    useEffect(() => {
        obtenerHistorial();
    }, [input]);

    return (
        <div className={styles.calculator}>
            <div className={styles.calculadora}>
                {/* Pantalla */}
                <input
                    type="text"
                    value={input}
                    onChange={(e) => setInput(e.target.value)}
                    className={styles.display}
                    placeholder="0"
                />

                {/* Mensaje de error */}
                {errorMessage && <p className={styles.error}>{errorMessage}</p>}

                {/* Botones */}
                <div className={styles.buttons}>
                    {["7", "8", "9", "/"].map((btn) => (
                        <button key={btn} className={styles.button} onClick={() => handleInput(btn)}>
                            {btn}
                        </button>
                    ))}
                    {["4", "5", "6", "*"].map((btn) => (
                        <button key={btn} className={styles.button} onClick={() => handleInput(btn)}>
                            {btn}
                        </button>
                    ))}
                    {["1", "2", "3", "-"].map((btn) => (
                        <button key={btn} className={styles.button} onClick={() => handleInput(btn)}>
                            {btn}
                        </button>
                    ))}
                    {["0", ".", "C", "+"].map((btn) => (
                        <button
                            key={btn}
                            className={styles.button}
                            onClick={btn === "C" ? clearInput : () => handleInput(btn)}
                        >
                            {btn}
                        </button>
                    ))}
                    <button className={`${styles.button} ${styles.spanTwo}`} onClick={calculateResult}>
                        =
                    </button>
                </div>
            </div>

            <div className={styles.historial}>
                {historiales.map((historial) => (
                    <div key={historial.id}>
                        <p>{historialResponse(historial.operation, historial.result)}</p>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default Calculator;