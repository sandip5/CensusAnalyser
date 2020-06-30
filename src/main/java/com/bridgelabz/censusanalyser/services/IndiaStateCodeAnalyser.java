package com.bridgelabz.censusanalyser.services;

import com.bridgelabz.censusanalyser.exception.CensusAnalyserException;
import com.bridgelabz.censusanalyser.model.IndiaCensusCSV;
import com.bridgelabz.censusanalyser.model.IndiaStateCodeCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IndiaStateCodeAnalyser {
        public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
            try {
                Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
                CsvToBeanBuilder<IndiaStateCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
                csvToBeanBuilder.withType(IndiaStateCodeCSV.class);
                csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
                CsvToBean<IndiaStateCodeCSV> csvToBean = csvToBeanBuilder.build();
                Iterator<IndiaStateCodeCSV> censusCSVIterator = csvToBean.iterator();
                Iterable<IndiaStateCodeCSV> csvIterable = () -> censusCSVIterator;
                int numberOfEntries = (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
                return numberOfEntries;
            } catch (RuntimeException e){
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.DELIMITER_AND_HEADER_PROBLEM);
            } catch (IOException e) {
                throw new CensusAnalyserException(e.getMessage(),
                        CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
        }
}
