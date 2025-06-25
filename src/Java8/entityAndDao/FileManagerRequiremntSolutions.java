package Java8.entityAndDao;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;



/*
*
* File(fileId=file2, fileName=document2.jpeg, fileSize=8603,
* fileType=image/jpeg, createdDate=2024-03-27T03:57:47.267,
* modifiedDate=2024-03-27T14:57:47.267,
* metadata={owner=eve, category=marketing},
* tags=[obsolete, archived, draft, confidential, shared, urgent, final, important],
* permissions=[File.Permission(userId=userE, accessLevel=SHARE), File.Permission(userId=userI, accessLevel=EXECUTE)],
* versionHistory={v2.0=[File.Version(versionId=v2.0.5, versionDate=Wed Jan 01 00:39:31 IST 2025, changes=Bug fixes),
* File.Version(versionId=v2.0.7, versionDate=Tue May 07 06:09:45 IST 2024, changes=Performance improvements)],
* v1.0=[File.Version(versionId=v1.0.2, versionDate=Tue Oct 29 23:04:39 IST 2024, changes=Initial upload),
* File.Version(versionId=v1.0.6, versionDate=Wed Jan 08 16:54:23 IST 2025, changes=Metadata update)]},
* location=Location(locationId=loc2, country=Country1, city=City9, address=Address2,
* coordinates={latitude=45.47469931451299, longitude=-129.7347078698495},
* nearbyLandmarks=[Landmark A, Landmark B, Landmark C]))
*
* */

public class FileManagerRequiremntSolutions {
    private static final List<File> fileManagers = FileManagerDAO.getAllFiles();

    public static void filterByFileSize(){
        fileManagers.stream()
                .filter(file -> file.getFileSize() < 5000)
                .toList()
                .forEach(System.out::println);
    }

    public static void getAllFileNames(){
        fileManagers.stream()
                .map(File::getFileName)
                .toList()
                .forEach(System.out::println);
    }

    public static void countFilesByOwner(String owner){
        var count = fileManagers.stream()
                .filter(file -> file.getMetadata()
                        .get("owner")
                        .equals(owner))
                .count();
        System.out.println(count);
    }

    public static void filterByTag(String tag){
        fileManagers.stream()
                .filter(file -> file.getTags().contains(tag))
                .toList()
                .forEach(System.out::println);
    }

    public static void sortByCreatedDate(){
        fileManagers.stream()
                .sorted(Comparator.comparing(File::getCreatedDate).reversed())
                .toList()
                .forEach(System.out::println);
    }

    public static void groupByFileType(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(File::getFileType))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void groupByCategory(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(file -> file.getMetadata().get("category")))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void totalFileSize(){
        var size = fileManagers.stream()
                .mapToLong(File::getFileSize)
                .sum();

        System.out.println("Total size of all files " + size);
    }

    public static void getAllTags(){
        fileManagers.stream()
                .flatMap(file -> file.getTags()
                        .stream())
                .distinct()
                .toList()
                .forEach(System.out::println);
    }

    public static void getAllFilesAboveAndBelowSizeThreshold(){
        fileManagers.stream()
                .collect(Collectors.partitioningBy(file -> file.getFileSize() > 5000))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void getMaximumSizeFile(){
        var file = fileManagers.stream()
                .max(Comparator.comparingLong(File::getFileSize)).orElse(null);
        System.out.println("Maximum file by size = " + file);
    }

    public static void getAverageFileSizePerCategory(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(file -> file.getMetadata()
                        .get("category"), Collectors.averagingLong(File::getFileSize)))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void listReadPermissionUserPerFile(){
//        fileManagers.stream()
//                .flatMap(file -> file.getPermissions()
//                        .stream()
//                        .filter(permission -> permission.getAccessLevel()
//                                .equalsIgnoreCase("READ"))
//                        .map(File.Permission::getUserId))
//                .toList()
//                .forEach(System.out::println);

        fileManagers.stream().collect(Collectors.toMap(File::getFileId, file -> file.getPermissions()
                        .stream()
                        .filter(permission -> permission.getAccessLevel()
                                .equalsIgnoreCase("READ"))
                        .map(File.Permission::getUserId)
                        .toList()))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void getAllVersionChangesEachFile(){
        fileManagers.stream()
                .collect(Collectors.toMap(File::getFileId,
                        file -> file.getVersionHistory()
                                .entrySet()
                                .stream()
                                .collect(Collectors.toMap(Map.Entry::getKey, listVersion -> listVersion
                                        .getValue()
                                        .stream()
                                        .map(File.Version::getChanges)
                                        .toList()))
                ))
                .entrySet()
                .forEach(System.out::println);

    }

    public static void groupByCountry(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(file -> file.getLocation().getCountry(),
                        Collectors.mapping(File::getFileId, Collectors.toList())))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void filterByModifiedDateRange(){
        fileManagers.stream()
                .filter(file -> file.getModifiedDate().isAfter(LocalDateTime.of(2012, 1, 1, 0, 0))
                        && file.getModifiedDate().isBefore(LocalDateTime.of(2025, 1, 1, 0,0)))
                .sorted(Comparator.comparing(File::getModifiedDate))
                .toList()
                .forEach(System.out::println);
    }

    public static void filterTagsForSpecificOwner(String owner){
        fileManagers.stream()
                .filter(file -> owner
                        .equalsIgnoreCase(file.getMetadata()
                                .get("owner")))
                .map(File::getTags)
                .toList()
                .parallelStream()
                .flatMap(Collection::parallelStream)
                .distinct()
                .toList()
                .forEach(System.out::println);
    }

    public static void filterLatestVersionForEachFile(){
        fileManagers.stream()
                .collect(Collectors.toMap(File::getFileName, file -> file.getVersionHistory()
                        .entrySet()
                        .stream()
                        .flatMap(version -> version
                                .getValue()
                                .stream()
                        )
                        .max(Comparator.comparing(File.Version::getVersionDate))
                        .stream()
                        .findFirst()
                        .orElse(new File.Version(null, null, null))))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void groupByOwnerAndThanGroupByLocation(){
        fileManagers.parallelStream()
                .collect(Collectors.groupingBy(file -> file.getMetadata().get("owner"),
                        Collectors.groupingBy(file -> file.getLocation().getCountry())))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void countFilesPerTag(){
//        fileManagers.stream()
//                .flatMap(file -> file.getTags()
//                        .stream()
//                        .map(tags -> new AbstractMap.SimpleEntry<>(tags, file)))
//                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey,
//                        Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())))
//                .entrySet()
//                .forEach(System.out::println);

        fileManagers.stream()
                .flatMap(file -> file.getTags().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void getCommonFileTypePerCountry(){
        fileManagers.parallelStream()
                .collect(Collectors.groupingBy(file -> file.getLocation()
                        .getCountry(), Collectors.collectingAndThen(Collectors.groupingBy(File::getFileType, Collectors.counting()),
                        map -> map.entrySet()
                                .parallelStream()
                                .max(Map.Entry.comparingByValue())
                                .map(Map.Entry::getKey)
                                .orElse("None")
                        )))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void concatenateFileNamesPerOwner(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(file -> file.getMetadata()
                                .get("owner"),
                        Collectors.mapping(File::getFileName,
                                Collectors.joining(","))))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void groupFilesByCityTypeAndGetSize(){
        fileManagers.parallelStream()
                .collect(Collectors.groupingBy(file -> file.getLocation()
                                .getCity(),
                        Collectors.groupingBy(File::getFileType,
                                Collectors.summarizingLong(File::getFileSize))
                       ))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void earliestCreatedDateByOwner(){
        fileManagers.stream()
                .collect(Collectors.groupingBy(file -> file.getMetadata()
                        .get("owner"),
                        Collectors.minBy(Comparator.comparing(File::getCreatedDate))))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void listAllUniquePermissionsAcrossFiles(){
        fileManagers.stream()
                .flatMap(file -> file.getPermissions()
                        .stream()
                        .distinct()
                ).toList()
                .forEach(System.out::println);
    }

    public static void countVersionPerFile(){
        fileManagers.stream()
                .collect(Collectors.toMap(File::getFileId,
                        file-> file.getVersionHistory()
                                .values()
                                .stream()
                                .mapToLong(Collection::size)
                                .sum()
                ))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void getFileWithMultipleTags(){
        fileManagers.stream()
                .filter(file -> file.getTags().size() > 1)
                .map(File::getFileName)
                .sorted()
                .toList()
                .forEach(System.out::println);
    }

    public static void groupFilesByMonthCreated(){
        fileManagers.parallelStream()
                .collect(Collectors.groupingBy(file -> file.getCreatedDate().getMonth(),
                        Collectors.mapping(File::getFileId, Collectors.toList())))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void getAverageFileSizeByTag(){
        fileManagers.parallelStream()
                .flatMap(file -> file.getTags()
                        .stream()
                        .map(tag-> new AbstractMap.SimpleEntry<>(tag, file.getFileSize())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.averagingLong(Map.Entry::getValue)))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void groupFilesBySpecificUserPermissionLevel(String user){
        fileManagers.parallelStream()
                .flatMap(file -> file.getPermissions()
                        .stream()
                        .filter(permission -> permission.getUserId().equalsIgnoreCase("userA"))
                        .map(permission -> new AbstractMap.SimpleEntry<>(permission.getAccessLevel(), file)))
                .collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey,
                        Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList())))
                .entrySet()
                .forEach(System.out::println);
    }

    public static void groupByCityAndTagUnderThat(){
//        fileManagers.stream()
//                .collect(Collectors.groupingBy(file -> file.getLocation().getCity(), Collectors.mapping(file -> file.)))
//                .entrySet()
//                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        groupFilesBySpecificUserPermissionLevel("userA");
    }
}
