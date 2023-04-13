    package com.example.simplycalculate;

    public class ControllerActivity {
        public double addition(ModelActivity cmodel){
            return cmodel.m_num1 + cmodel.m_num2;
        }

        public double subtraction(ModelActivity cmodel){
            return cmodel.m_num1 - cmodel.m_num2;
        }

        public double multiplication(ModelActivity cmodel) { return cmodel.m_num1 * cmodel.m_num2;
        }

        public double divistion(ModelActivity cmodel){
            return cmodel.m_num1 / cmodel.m_num2;
        }
    }