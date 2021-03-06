<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<div class="col-md-12 tira-padding" style="padding-bottom: 20px">
    <div class="col-md-12 tira-padding">
        <div class="col-md-3">Valor do Item</div>
        <div class="col-md-2">

            <!--  -->
            <input name=valorItemTrans id="itemValor1" type="text" class="form-control" style="width: 110px" value='<fmt:formatNumber value="${param.valorItem}" pattern="#,##0.00"/>' onblur="calculaDiferenca();" />
            <!--  -->

        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12 tira-padding">
        <div class="col-md-3">Valor da
            Contratação&nbsp*&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</div>
        <div class="col-md-2">

            <!--  -->
            <input name="valorContratacaoTrans" id="contratacaoValor1" type="text" class="form-control" style="width: 110px"  value='<fmt:formatNumber value="${param.valorContratacao}" pattern="#,##0.00"/>' onblur="calculaDiferenca();" />
            <!--  -->

        </div>
    </div>
</div>

<div class="row">
    <div class="col-md-12 tira-padding">
        <div class="col-md-3" style="padding: 6px 0 6px 20px;">
            Diferença&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</div>
        <div class="col-md-3" id="diferenca" style="padding: 8px 0 8px 14px; width: 110px; background: #ddd; margin-left: 15px; border-radius: 5px; height: 34px">

            <!--  -->
            <input name="diferencaTrans" type="hidden"  value="<fmt:formatNumber value="${param.diferenca}" pattern="#,##0.00"/>"	id="diferencaValor1" />
            <span style="font-weight: bold;" id="difer"><fmt:formatNumber value="${param.diferenca}" pattern="#,##0.00" /></span>
            <!--  -->

        </div>
    </div>
    <div class="col-md-5 form-inline" style="padding: 6px 0 6px 20px;">
        Atribuir diferença
        <br />
        <br />
        <c:choose>

            <c:when test="${empty producaoP ||
            				producaoP.temContratacao == false
                    && producaoP.temMesmoFornecedor == false 
                    && producaoP.temOutroFornecedor == false}">
                <input name="infoForn" type="radio" checked="checked" value="0" onclick="atribuirFornecedor()">&nbsp&nbspMesmo Fornecedor
                <input name="infoForn" type="radio" value="1" onclick="atribuirOutroFornecedor()" style="margin-left: 120px;">&nbsp&nbspOutro Fornecedor
            </c:when>

            <c:when test="${not empty producaoP
                    && producaoP.temContratacao == false
                    && producaoP.temMesmoFornecedor == true 
                    && producaoP.temOutroFornecedor == false}">
                <input type="radio" name="infoForn" checked="checked" value="0" onclick="atribuirFornecedor()">&nbsp&nbspMesmo Fornecedor
                <input type="radio" name="infoForn" value="1" onclick="atribuirOutroFornecedor()" style="margin-left: 120px;">&nbsp&nbspOutro Fornecedor

            </c:when>

            <c:when test="${not empty producaoP
                    && producaoP.temContratacao == true
                    && producaoP.temMesmoFornecedor == true 
                    && producaoP.temOutroFornecedor == false}">
                <input type="radio" name="infoForn" checked="checked" value="0" onclick="atribuirFornecedor()">&nbsp&nbspMesmo Fornecedor
                <input type="radio" name="infoForn" value="1" onclick="atribuirOutroFornecedor()" style="margin-left: 120px;">&nbsp&nbspOutro Fornecedor

            </c:when>

            <c:when test="${not empty producaoP
                    && producaoP.temContratacao == true
                    && producaoP.temMesmoFornecedor == false 
                    && producaoP.temOutroFornecedor == true}">
                <input type="radio" name="infoForn" value="0" onclick="atribuirFornecedor()">&nbsp&nbspMesmo Fornecedor
                <input type="radio" name="infoForn" value="1" checked="checked" onclick="atribuirOutroFornecedor()" style="margin-left: 120px;">&nbsp&nbspOutro Fornecedor

            </c:when>

        </c:choose>
        <!--  -->
    </div>
</div>


	<div class="col-md-8 
	     <c:choose>
		     <c:when test="${not empty producaoP 
			     && producaoP.temContratacao == true
			     && producaoP.temMesmoFornecedor == false 
			     && producaoP.temOutroFornecedor == true}">
		     display-none
			</c:when>
			<c:otherwise>
			
			</c:otherwise>		
		 </c:choose>
	" id="mesmoFornecedorDiv" style="margin: 10px 0; background: #fff; padding: 3px 0px 30px 30px">


<h3>Mesmo Fornecedor</h3>
<div class="row">
    Imposto
    <div class="col-md-2  input-group">

        <!--  -->
        <input name="impostoTrans" id="impostoMesmoFornecedor" class="form-control"  value="${param.imposto}" style="text-align: center; font-weight: bolder; font-size: 18px;" onblur="calculaDiferencaFornecedor();" />
        <!--  -->

        <span class="input-group-addon" id="sizing-addon2">%</span>
    </div>
</div>

<div class="row col-md-9">
    Valor de Pagamento

    <!--  -->
    <input name="valorDePagamentoContratacaoTrans" id="valorPagamentoMesmoFornecedor" value='<fmt:formatNumber value="${param.valorDePagamentoContratacao}" pattern="#,##0.00" />'  class="form-control real input-180px" style="font-weight: bolder; font-size: 20px;" />
    <!--  -->

</div>

</div>

<div class="row">
    <div class="col-md-12 tira-padding">

        <div class="col-md-8 
             <c:choose>
	             <c:when test="${not empty producaoP 
	             && producaoP.temContratacao == true
	             && producaoP.temMesmoFornecedor == false 
	             && producaoP.temOutroFornecedor == true}">
	
	             </c:when>
	             <c:otherwise>
	                display-none
	             </c:otherwise>		
            </c:choose>
            " id="outroFornecedorDiv" style="margin: 10px 0; background: #fff; padding: 3px 0px 30px 30px">


            <h3>Outro fornecedor</h3>
            <div class="row">
                Selecione o fornecedor
                <select class="form-control input-180px" id="idEmpresa" name="idFornecedorOutroTrans">
                    <option value="${IdOutroFornecedor}">${OutroFornecedor}</option>
                    <c:forEach items="${fornecedoresLista}" var="fornecedoresLista">
                        <option value="${fornecedoresLista[0]}">${fornecedoresLista[1]}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row">
                Imposto
                <div class="col-md-2  input-group">
                    <!--  -->
                    <input name="impostoOutroTrans" value="${OutroImposto}" class="form-control" style="text-align: center; font-weight: bolder; font-size: 18px;"  id="impostoOutroFornecedor" onblur="calculaDiferencaOutroFornecedor();" />
                    <!--  -->
                    <span class="input-group-addon" id="sizing-addon2">%</span>
                </div>
            </div>

            <div class="row col-md-9">
                Valor de Pagamento
<!--  -->			
                <input name="valorDePagamentoContratacaoOutroFornecedorTrans" value="<fmt:formatNumber value="${OutroValor}" pattern="#,##0.00" />" class="form-control real input-180px"  style="text-align: center; font-weight: bolder; font-size: 20px;" id="valorPagamentoOutroFornecedor" />
<!--  -->
            </div>
            <div class="row col-md-5">
                Data Pagamento
                <input type="text" name="dataPgtoOutroFornecedorTrans" value='<fmt:formatDate value="${OutroDataPgto}" pattern="dd/MM/yyyy" />' class="form-control data" style="width: 110px" value="<fmt:formatDate value="${prazoEntrega}" pattern="dd/MM/yyyy"/> " name="pEntrega" />
            </div>
        </div>
    </div>
</div>
