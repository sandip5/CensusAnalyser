package com.bridgelabz.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {

        @CsvBindByName(column = "State Name", required = true)
        public String state;

        @CsvBindByName(column = "TIN", required = true)
        public int tin;

        @CsvBindByName(column = "StateCode", required = true)
        public int StateCode;

        @Override
        public String toString() {
            return "IndiaCensusCSV{" +
                    "State Name='" + state + '\'' +
                    ", TIN='" + tin + '\'' +
                    ", StateCode='" + StateCode + '\'' +
                    '}';
        }

}
