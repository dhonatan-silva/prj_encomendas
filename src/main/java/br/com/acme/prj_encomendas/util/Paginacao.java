///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.com.acme.prj_encomendas.util;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author frasilva
// * @param <T>
// */
//public class Paginacao<T> {
//    
//    private List<T> listaDados;
//    private List<T> listaPaginada;
//    private Integer paginaAtual;
//    private Boolean disabledBtnFirst;
//    private Boolean disabledBtnBack;
//    private Boolean disabledBtnNext;
//    private Boolean disabledBtnLast;
//    private Integer allPages;
//    
//    public Paginacao() {
//        paginaAtual = 0;
//        allPages = 0;
//    }
//    
//    public void firstPage() {
//        paginaAtual = 0;
//        paginar();
//        setDisabledBtnFirst(true);
//        setDisabledBtnBack(true);
//        setDisabledBtnNext(false);
//        setDisabledBtnLast(false);
//    }
//    
//    public void backPage() {
//        paginaAtual--;
//        paginar();
//        setDisabledBtnNext(false);
//        setDisabledBtnLast(false);
//        if (getPaginaAtual() == 0) {
//            setDisabledBtnFirst(true);
//            setDisabledBtnBack(true);
//        }
//    }
//    
//    public void nextPage() {
//        paginaAtual++;
//        paginar();
//        if (getPaginaAtual() == (allPages - 1)) {
//            setDisabledBtnNext(true);
//            setDisabledBtnLast(true);
//        }
//        setDisabledBtnFirst(false);
//        setDisabledBtnBack(false);
//    }
//    
//    public void lastPage() {
//        paginaAtual = allPages - 1;
//        paginar();
//        setDisabledBtnNext(true);
//        setDisabledBtnLast(true);
//        setDisabledBtnFirst(false);
//        setDisabledBtnBack(false);
//        
//    }
//    
//    public void paginar() {
//        setListaPaginada(new ArrayList<>());
//        List<List<T>> listaPaginas = new ArrayList<>();
//        List<T> listaAux = new ArrayList<>();
//        int registrosPorPagina = 10;
//        //int paginas = 0;
//        int resto = 0;
//        int total = getListaDados().size();
//        
//        if (total > registrosPorPagina) {
//            allPages = total / registrosPorPagina;
//            resto = total % registrosPorPagina;
//            
//            for (int i = 0; i < total; i++) {
//                listaAux.add(getListaDados().get(i));
//                if (listaAux.size() == registrosPorPagina) {
//                    listaPaginas.add(listaAux);
//                    listaAux = new ArrayList<>();
//                }
//            }
//            //adicionar resto
//            if (resto > 0) {
//                listaAux = new ArrayList<>();
//                allPages += 1;
// 
//                int init = total - resto;
//                for (int i = init; i < total; i++) {
//                    listaAux.add(getListaDados().get(i));
//                }
//                listaPaginas.add(listaAux);
//                
//            }
//        } else {
//            listaPaginas.add(getListaDados());
//        }
//        getListaPaginada().addAll(listaPaginas.get(getPaginaAtual()));
//        
//        if (allPages == 0) {
//            setDisabledBtnFirst(true);
//            setDisabledBtnBack(true);
//            setDisabledBtnNext(true);
//            setDisabledBtnLast(true);
//        }
//    }
//    
//    public List<T> getListaDados() {
//        return listaDados;
//    }
//    
//    public void setListaDados(List<T> listaDados) {
//        this.listaDados = listaDados;
//    }
//    
//    public List<T> getListaPaginada() {
//        return listaPaginada;
//    }
//    
//    public void setListaPaginada(List<T> listaPaginada) {
//        this.listaPaginada = listaPaginada;
//    }
//    
//    public Integer getPaginaAtual() {
//        return paginaAtual;
//    }
//    
//    private void setPaginaAtual(Integer paginaAtual) {
//        this.paginaAtual = paginaAtual;
//    }
//    
//    public Boolean getDisabledBtnFirst() {
//        return disabledBtnFirst;
//    }
//    
//    public void setDisabledBtnFirst(Boolean disabledBtnFirst) {
//        this.disabledBtnFirst = disabledBtnFirst;
//    }
//    
//    public Boolean getDisabledBtnBack() {
//        return disabledBtnBack;
//    }
//    
//    public void setDisabledBtnBack(Boolean disabledBtnBack) {
//        this.disabledBtnBack = disabledBtnBack;
//    }
//    
//    public Boolean getDisabledBtnNext() {
//        return disabledBtnNext;
//    }
//    
//    public void setDisabledBtnNext(Boolean disabledBtnNext) {
//        this.disabledBtnNext = disabledBtnNext;
//    }
//    
//    public Boolean getDisabledBtnLast() {
//        return disabledBtnLast;
//    }
//    
//    public void setDisabledBtnLast(Boolean disabledBtnLast) {
//        this.disabledBtnLast = disabledBtnLast;
//    }
//
//    public Integer getAllPages() {
//        return allPages;
//    }
//
//    public void setAllPages(Integer allPages) {
//        this.allPages = allPages;
//    }
//    
//}
