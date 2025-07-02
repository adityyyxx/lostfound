package com.lostfound.lostfound.controller;

import com.lostfound.lostfound.model.LostItem;
import com.lostfound.lostfound.model.FoundItem;
import com.lostfound.lostfound.repository.LostItemRepository;
import com.lostfound.lostfound.repository.FoundItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    private LostItemRepository lostItemRepo;

    @Autowired
    private FoundItemRepository foundItemRepo;

    // Load home page
    @GetMapping("/")
    public String home() {
        return "index"; // loads index.html from templates
    }

    // ---------------- LOST --------------------

    @GetMapping("/lost")
    public String showLostForm() {
        return "lost"; // loads lost.html from templates
    }

    @PostMapping("/submit-lost")
    public String submitLostItem(
            @RequestParam("item_name") String itemName,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("date_lost") String dateLost,
            @RequestParam("location") String location,
            @RequestParam("contact") String contact,
            @RequestParam("image") MultipartFile image) throws IOException {

        LostItem item = new LostItem();
        item.setItemName(itemName);
        item.setDescription(description);
        item.setCategory(category);
        item.setDateLost(dateLost);
        item.setLocation(location);
        item.setContact(contact);

        // ✅ Handle image upload with safe path
        if (!image.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filePath = uploadDir + image.getOriginalFilename();
            image.transferTo(new File(filePath));

            item.setImagePath(filePath);
        }

        lostItemRepo.save(item);
        return "thankyou"; // thankyou.html
    }

    // ---------------- FOUND --------------------

    @GetMapping("/found")
    public String showFoundForm() {
        return "found"; // loads found.html from templates
    }

    @PostMapping("/submit-found")
    public String submitFoundItem(
            @RequestParam("item_name") String itemName,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("date_found") String dateFound,
            @RequestParam("location") String location,
            @RequestParam("contact") String contact,
            @RequestParam("image") MultipartFile image) throws IOException {

        FoundItem item = new FoundItem();
        item.setItemName(itemName);
        item.setDescription(description);
        item.setCategory(category);
        item.setDateFound(dateFound);
        item.setLocation(location);
        item.setContact(contact);

        // ✅ Handle image upload with safe path
        if (!image.isEmpty()) {
            String uploadDir = System.getProperty("user.dir") + "/uploads/";
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            String filePath = uploadDir + image.getOriginalFilename();
            image.transferTo(new File(filePath));

            item.setImagePath(filePath);
        }

        foundItemRepo.save(item);
        return "thankyou"; // thankyou.html
    }
}
