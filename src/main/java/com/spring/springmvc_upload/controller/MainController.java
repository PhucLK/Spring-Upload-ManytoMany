package com.spring.springmvc_upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.springmvc_upload.entities.Images;
import com.spring.springmvc_upload.entities.Product;
import com.spring.springmvc_upload.entities.Size;
import com.spring.springmvc_upload.service.ImageService;
import com.spring.springmvc_upload.service.ProductService;

@Controller
public class MainController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private HttpServletRequest request;

	private Product p;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String home(Model model, @RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "message", required = false) String message) {
		model.addAttribute("ListProduct", productService.getAllProduct());
		model.addAttribute("status", status);
		model.addAttribute("message", message);
		return "lists";
	}

	@GetMapping("/view")
	public String view(@RequestParam("id") int id, Model model) {
		for (Product p : productService.getAllProduct()) {
			if (p.getId() == id) {
				model.addAttribute("product", p);
				break;
			}
		}
		return "view";
	}

	@PostMapping("/addImage")
	public String Image(@ModelAttribute("images") Images images, Model model) {
		String[] size = p.getSize();
		// System.out.println(size[0]);
		if (images != null && !images.getImage().isEmpty()) {

			String realPathtoUploads = request.getServletContext().getRealPath("/");
			List<Images> listImages = new ArrayList<Images>();
			List<MultipartFile> listMultipartFile = images.getImage();
			for (MultipartFile multipartFile : listMultipartFile) {

				Images image = new Images();
				String fileName = multipartFile.getOriginalFilename();
				image.setName(fileName);
				p = productService.saveProductS(p, size);
				image.setProduct(p);
				image = imageService.saveImage(image);
				listImages.add(image);

				String array[] = realPathtoUploads.split("\\.");
//				for (String a : array) {
//					System.out.println(a);
//				}
				String path = array[0] + "MVCupload\\src\\main\\webapp\\resources\\images\\" + fileName;

				File imageFile = new File(path);
				try {
					multipartFile.transferTo(imageFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
//			p.setImages(listImages);
//			productService.save(p);
			return "redirect:/home";
		} else {
			model.addAttribute("image", new Images());
		}
		model.addAttribute("action", "addImage");
		return "image-form";

	}

	@GetMapping("/addProduct")
	public String addProduct(Model model) {
		// Product product = ;
		model.addAttribute("product", new Product());
		model.addAttribute("action", "addProduct");
		model.addAttribute("submit", "Add Product");
		String sizes[] = { "S", "M", "L", "Xl" };
		// product.getSizes()
		model.addAttribute("sizes", sizes);
		return "product-form";
	}

	@PostMapping("/addProduct")
	public String saveProduct(Model model, @ModelAttribute("product") Product product) {
		p = product;
		model.addAttribute("images", new Images());
		model.addAttribute("action", "addImage");
		return "image-form";
	}

	@PostMapping("/editProduct")
	public String editProduct(Model model, @ModelAttribute("product") Product product) {
		p = product;
		model.addAttribute("images", new Images());
		model.addAttribute("action", "addImage");
		return "image-form";
	}

	@GetMapping("/editProduct/{id}")
	public String editProduct(Model model, @PathVariable("id") int id) {

		Product product = productService.findProduct(id);

		if (product != null) {
			List<Size> list = product.getSizes();
			List<String> String = new ArrayList<String>();
			for (Size s : list) {
				String.add(s.getName());
			}

			model.addAttribute("product", product);
			model.addAttribute("action", "editProduct");

			String sizes[] = { "S", "M", "L", "Xl" };
			model.addAttribute("sizes", sizes);

			model.addAttribute("sizeCheck", String);
			model.addAttribute("lenght", String.size());
			model.addAttribute("submit", "Edit Product");
			return "product-form";
		} else {
			model.addAttribute("message", "Not exist data !!!");
			return "redirect:/home";
		}
	}

	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(Model model, @PathVariable("id") int id) {

		Product product = productService.findProduct(id);

		if (product != null) {
			if (!productService.isDelete(id)) {
				return "redirect:/home?status=ok&message=Delete Success !";
			} else {
				return "redirect:/home?status=fail&message=Delete Fail !";
			}
		} else {
			model.addAttribute("message", "Not exist data !!!");
			return "redirect:/home";
		}
	}
}
