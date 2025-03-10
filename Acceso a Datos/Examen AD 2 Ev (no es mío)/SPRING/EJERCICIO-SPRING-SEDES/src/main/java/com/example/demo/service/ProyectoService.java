package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.Departamento;
import com.example.demo.modelo.Empleado;
import com.example.demo.modelo.EmpleadoDatosProf;
import com.example.demo.modelo.EmpleadoDatosProfPK;
import com.example.demo.modelo.Proyecto;
import com.example.demo.modelo.ProyectoSede;
import com.example.demo.modelo.ProyectoSedePK;
import com.example.demo.modelo.Sede;
import com.example.demo.repository.DepartamentoRepository;
import com.example.demo.repository.EmpleadoDatosProfRepository;
import com.example.demo.repository.EmpleadoRepository;
import com.example.demo.repository.ProyectoRepository;
import com.example.demo.repository.ProyectoSedeRepository;
import com.example.demo.repository.SedeRepository;

@Service
public class ProyectoService {
	
	public static Scanner sc = new Scanner(System.in);
	
	@Autowired
    private ProyectoRepository proyRepo;
	
	@Autowired
    private SedeRepository sedeRepo;
	
	@Autowired
    private ProyectoSedeRepository proySedeRepo;
	
	@Autowired
    private DepartamentoRepository depRepo;
	
	@Autowired
    private EmpleadoRepository empRepo;
	
	@Autowired
    private EmpleadoDatosProfRepository datosEmpRepo;
	
	@Transactional
    public void añadirProyecto(int id_proy, int id_sede) {
		
    	Proyecto p = proyRepo.findById(id_proy).get();
    	Sede s = sedeRepo.findById(id_sede).get();
    	ProyectoSede ps = new ProyectoSede();
    	
    	//Date fecha = java.sql.Date.valueOf(java.time.LocalDate.now());
    	
    	if(p!=null) {
    		if(s!=null) {
    			ps.setId(new ProyectoSedePK(id_proy, id_sede));
    			ps.setProyecto(p);
    			ps.setSede(s);
    			ps.setF_inicio(p.getF_inicio());
    			ps.setF_fin(p.getF_fin());
    			
    			proySedeRepo.save(ps);
    			proySedeRepo.flush();
    			System.out.println("Proyecto añadido a la sede");
    		}else {
    			System.out.println("Id de sede no encontrado");
    		}
    	}else {
    		System.out.println("Id de proyecto no encontrado");
    	}
    }
	
	@Transactional
	public void modificarDatosEmpleado(String dni, String categoria, double sueldo) {
		
		EmpleadoDatosProfPK datosEmpPK = new EmpleadoDatosProfPK(dni);
		EmpleadoDatosProf datosEmp = datosEmpRepo.findById(datosEmpPK).get();
		
		if(datosEmp!=null) {
			if(!datosEmp.getCategoria().equals(categoria) && datosEmp.getSueldo_bruto_anual()!=sueldo) {
				datosEmp.setCategoria(categoria);
				datosEmp.setSueldo_bruto_anual(sueldo);
				
				datosEmpRepo.save(datosEmp);
				datosEmpRepo.flush();
			}else {
				System.out.println("Los datos ya existen. Quieres modificarlos? (si/no):");
				String res = sc.next();
				
				if(res.equals("si")) {
					datosEmp.setCategoria(categoria);
					datosEmp.setSueldo_bruto_anual(sueldo);
					
					datosEmpRepo.save(datosEmp);
					datosEmpRepo.flush();
					System.out.println("Datos modificados");
				}else if(res.equals("no")) {
					System.out.println("Datos no modificados");
				}else {
					System.out.println("Opcion no valida");
				}
			}
		}else {
			System.out.println("DNI de empleado no encontrado");
		}
	}
	
	@Transactional
	public void añadirSede(String nomProy) {
		
		int cont = 0;
		int idProy = 0;
		int idSede = 0;
		
		List<Proyecto> proyectos = proyRepo.findAll();
		
		for(Proyecto p : proyectos) {
			if(p.getNom_proy().equals(nomProy)) {
				cont++;
				idProy = p.getId_proy();
			}
		}
		
		if(cont>1) {
			System.out.println("Cual es el id del proyecto?:");
			idProy = sc.nextInt();
		}
		
		System.out.println("Cual es el nombre de la sede?:");
		String nomSede = sc.next();
		
		Sede s = new Sede();
		s.setNom_sede(nomSede);
		
		sedeRepo.save(s);
		sedeRepo.flush();
		
		List<Sede> sedes = sedeRepo.findAll();
		
		for(Sede se : sedes) {
			if(se.getNom_sede().equals(nomSede)) {
				idSede = se.getId_sede();
			}
		}
		
		ProyectoSedePK psPK = new ProyectoSedePK(idSede, idProy);
		ProyectoSede ps = new ProyectoSede();
		
		ps.setId(psPK);
		ps.setF_inicio(new Date(2025-03-01));
		ps.setF_fin(new Date(2026-03-01));
		
		proySedeRepo.save(ps);
		proySedeRepo.flush();
	}
	
	@Transactional
	public void eliminarSede(int idSede) {
		
		Sede s = sedeRepo.findById(idSede).get();
		
		if(s!=null) {
			sedeRepo.delete(s);
			System.out.println("Sede eliminada");
		}else {
			System.out.println("Id de sede no encontrado");
		}
	}
	
	@Transactional
	public void mostrarSedeConMasProyectos() {
		
		int i = 0;
		int max = 0;
		int sedeMax = 0;
		
		List<ProyectoSede> ps = proySedeRepo.findAll();
		List<Integer> ids = new LinkedList<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		for(ProyectoSede p : ps) {
			ProyectoSedePK pk = p.getId();
			int idSede = pk.getId_sede();
			
			ids.add(idSede);
			
			for(Integer id : ids) {
				if(id==idSede) {
					map.put(id, i+1);
				}else {
					map.put(id, i);
				}
			}
		}
		
		for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if(entry.getValue()>max) {
				max = entry.getValue();
				sedeMax = entry.getKey();
			}
		}
		
		System.out.println("La sede con mas proyectos es: "+sedeMax+" con "+max+" proyectos");
	}
	
	@Transactional
	public void mostrarNombreDepartamentoConMasSueldo() {
		
		double max = 0;
		String nombre = "";
		
		List<Departamento> departamentos = depRepo.findAll();
		
		for(Departamento d :  departamentos) {
			double sueldo = 0;
			
			for(Empleado e : d.getEmpleados()) {
				for(EmpleadoDatosProf de : e.getDatosEmpleados()) {
					sueldo += de.getSueldo_bruto_anual();
				}
			}
			
			if(sueldo>max) {
				max = sueldo;
				nombre = d.getNom_depto();
			}
		}
		
		System.out.println("El departamento que mas gasta en sueldo es: "+nombre);
	}
	
	/**
	 * subir un 10% el sueldo de todos los empleados de 
	 * un mismo departamento
	 * @param idDep
	 */
	@Transactional
	public void subirSueldoEmpleados(int idDep) {
		
		List<Empleado> empleados = empRepo.findAll();
		
		for(Empleado e : empleados) {
			if(e.getDepartamento().getId_depto()==idDep) {
				String dni = e.getDni();
				
				for(EmpleadoDatosProf de : e.getDatosEmpleados()) {
					if(de.getId().equals(e.getDni())) {
						double subida = de.getSueldo_bruto_anual()*0.1;
						de.setSueldo_bruto_anual(de.getSueldo_bruto_anual()+subida);
						
						datosEmpRepo.save(de);
					}
				}
			}
		}
		
		datosEmpRepo.flush();
	}

}
